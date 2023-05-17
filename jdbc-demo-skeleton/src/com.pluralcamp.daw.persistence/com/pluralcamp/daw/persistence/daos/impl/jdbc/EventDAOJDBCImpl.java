package com.pluralcamp.daw.persistence.daos.impl.jdbc;

import com.pluralcamp.daw.entities.core.Color;
import com.pluralcamp.daw.entities.core.Event;
import com.pluralcamp.daw.persistence.daos.contracts.EventDAO;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventDAOJDBCImpl implements EventDAO {

    // Para búsquedas sin filtros
    public static final int DEFAULT_VALUE = 0;
    public static final String EMPTY = "";

    // Para crear conexiones
    private static Connection createConnexion() throws DAOException {

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "alex", "12345678");
            return conn;
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    // Get formatted fields
    private LocalDate getLocaDate(Date fechaSQL) {

        return fechaSQL.toLocalDate();

    }

    private LocalTime getLocaTime(Time time) {

        return time.toLocalTime();

    }

    // Para recoger datos

    private static int getLastID() throws DAOException {

        try ( // Crear connexion
                Connection conn = createConnexion();
                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall("SELECT id, name, red, green, blue FROM colors ORDER BY ID DESC LIMIT 1");) {

            // Leer consulta
            try (ResultSet reader = sentSQL.executeQuery();) {
                if (reader.next()) {
                    return Integer.valueOf(reader.getString("id"));
                } else {
                    throw new SQLException("No records found in the table.");
                }
            }

        } catch (SQLException ex) {

            // Logger
            throw new DAOException(ex);

        }

    }

    private List<Event> readQuery(ResultSet reader, int offset, int count) throws SQLException, DAOException {

        List<Event> events = new ArrayList<Event>();

        // Si la query no retorna res
        if (!reader.isBeforeFirst()) {
            throw new SQLException("No records found in the table.");
        }

        /***
         * Si el número de fila es mayor al offset y
         * el counterCount es más pequeño o igual al número de elementos límite a
         * mostrar, añadir a la lista.
         */
        int counterOffset = 1;
        int counterCount = 1;
        while (reader.next()) {

            if (counterOffset > offset && counterCount <= count) {

                // Convert sql dates to project required format dates
                LocalDate fecha = this.getLocaDate(reader.getDate("date"));
                LocalTime startTime = this.getLocaTime(reader.getTime("startTime"));
                LocalTime endTime = this.getLocaTime(reader.getTime("endTime"));

                // Get backgroundColor and Text Color
                ColorDAOJDBCImpl colorDAO = new ColorDAOJDBCImpl();

                Color backgroundColor = colorDAO.getColorById(reader.getLong("backgroundColor"));
                Color textColor = colorDAO.getColorById(reader.getLong("textColor"));

                // Get visible
                boolean visible = reader.getBoolean("visible");

                // Create Event
                var e = new Event(reader.getString("name"), fecha, startTime, endTime, reader.getString("place"),
                        reader.getString("description"), backgroundColor, textColor, visible);
                e.setId(reader.getLong("id"));
                events.add(e);

                counterCount++;
            }

            counterOffset++;
        }

        return events;

    }

    @Override
    public Event getEventById(long id) throws DAOException {

        Event e = null;

        try ( // Crear connexion
                Connection conn = createConnexion();
                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall("SELECT id, name, date, startTime, endTime, place, description," +
                                "backgroundColor, textColor, visible FROM events where id = ?");) {

            sentSQL.setLong(1, id);

            // Leer consulta
            try (ResultSet reader = sentSQL.executeQuery();) {
                if (reader.next()) {
                    
                    // Convert sql dates to project required format dates
                    LocalDate fecha = this.getLocaDate(reader.getDate("date"));
                    LocalTime startTime = this.getLocaTime(reader.getTime("startTime"));
                    LocalTime endTime = this.getLocaTime(reader.getTime("endTime"));

                    // Get backgroundColor and Text Color
                    ColorDAOJDBCImpl colorDAO = new ColorDAOJDBCImpl();

                    Color backgroundColor = colorDAO.getColorById(reader.getLong("backgroundColor"));
                    Color textColor = colorDAO.getColorById(reader.getLong("textColor"));

                    // Get visible
                    boolean visible = reader.getBoolean("visible");

                    // Create Event
                    e = new Event(reader.getString("name"), fecha, startTime, endTime, reader.getString("place"),
                            reader.getString("description"), backgroundColor, textColor, visible);
                    e.setId(reader.getLong("id"));
                }
            }

        } catch (SQLException ex) {

            // Logger
            throw new DAOException(ex);

        }

        return e;
    }

    @Override
    public List<Event> getEvents() throws DAOException {
        List<Event> events = getEvents(DEFAULT_VALUE, DEFAULT_VALUE);

        return events;
    }

    @Override
    public List<Event> getEvents(int offset, int count) throws DAOException {
        List<Event> events = getEvents(EMPTY, offset, count);

        return events;
    }

    @Override
    public List<Event> getEvents(String searchTerm) throws DAOException {
        List<Event> events = getEvents(searchTerm, DEFAULT_VALUE, DEFAULT_VALUE);

        return events;
    }

    @Override
    public List<Event> getEvents(String searchTerm, int offset, int count) throws DAOException {
        List<Event> events = new ArrayList<Event>();

        try ( // Crear conexión
                Connection conn = createConnexion();

                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall("SELECT id, name, date, startTime, endTime, place, description," +
                        "backgroundColor, textColor, visible FROM events where name like ?");) {

            // filter
            sentSQL.setString(1, "%" + searchTerm + "%");

            // Comprobar si no hay límite de filas a devolver
            if (count == 0) {
               count = getLastID();
            }

            // Leer consulta
            ResultSet reader = sentSQL.executeQuery();
            events = this.readQuery(reader, offset, count);

        } catch (SQLException ex) {

            // Logger
            throw new DAOException(ex);

        }

        return events;
    }
}
