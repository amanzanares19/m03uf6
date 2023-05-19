package com.pluralcamp.daw.persistence.daos.impl.jdbc;

import com.pluralcamp.daw.entities.core.Color;
import com.pluralcamp.daw.persistence.daos.contracts.ColorDAO;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorDAOJDBCImpl implements ColorDAO {

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

    // Para recoger datos
    private List<Color> readQuery(ResultSet reader, int offset, int count) throws SQLException {

        List<Color> colors = new ArrayList<Color>();

        // Si la query no retorna res
        if (!reader.isBeforeFirst()) {
            throw new SQLException("No records found in the table.");
        }

        /***
         * Si el número de fila es mayor al offset y 
         * el counterCount es más pequeño o igual al número de elementos límite a mostrar, añadir a la lista.
         *  */ 
        int counterOffset = 1;
        int counterCount = 1;
        while (reader.next()) {

            if (counterOffset > offset && counterCount <= count) {
                
                var c = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                        reader.getInt("blue"));
                c.setId(reader.getLong("id"));
                colors.add(c);

                counterCount++;
            }

            counterOffset++;
        }

        return colors;

    }

    private static int getLastID() throws DAOException {

        try ( // Crear connexion
                Connection conn = createConnexion();
                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall("CALL getLastId()");) {

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

    @Override
    public Color getColorById(long id) throws DAOException {

        // Objectes que calen:
        // 1er objecte - Connexio, via DriverManager de JDBC
        // 2n objecte - Obrir un canal de Consulta - PraparedStatement
        // 2.1 - Enviar la consulta SQL
        // 3er objecte - Obrir un canal de Lectura, un cursor - ResultSet
        Color c = null;

        try ( // Crear connexion
                Connection conn = createConnexion();
                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall("CALL getColorById(?)");) {

            sentSQL.setLong(1, id);

            // Leer consulta
            try (ResultSet reader = sentSQL.executeQuery();) {
                if (reader.next()) {
                    c = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                            reader.getInt("blue"));
                    c.setId(reader.getLong("id"));
                }
            }

        } catch (SQLException ex) {

            // Logger
            throw new DAOException(ex);

        }

        return c;
    }

    // Utilizando la técnica de constructor chaining
    @Override
    public List<Color> getColors() throws DAOException {

        List<Color> colors = getColors(DEFAULT_VALUE, DEFAULT_VALUE);

        return colors;

    }

    @Override
    public List<Color> getColors(int offset, int count) throws DAOException {

        List<Color> colors = getColors(EMPTY, offset, count);

        return colors;

    }

    @Override
    public List<Color> getColors(String searchTerm) throws DAOException {

        List<Color> colors = getColors(searchTerm, DEFAULT_VALUE, DEFAULT_VALUE);

        return colors;
    }

    @Override
    public List<Color> getColors(String searchTerm, int offset, int count) throws DAOException {

        List<Color> colors = new ArrayList<Color>();

        try ( // Crear conexión
                Connection conn = createConnexion();

                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall("CALL getColors(?)");) {

            // filter
            sentSQL.setString(1, "%" + searchTerm + "%");

            // Comprobar si no hay límite de filas a devolver
            if (count == 0) {
               count = getLastID();
            }

            // Leer consulta
            ResultSet reader = sentSQL.executeQuery();
            colors = this.readQuery(reader, offset, count);

        } catch (SQLException ex) {

            // Logger
            throw new DAOException(ex);

        }

        return colors;

    }
}
