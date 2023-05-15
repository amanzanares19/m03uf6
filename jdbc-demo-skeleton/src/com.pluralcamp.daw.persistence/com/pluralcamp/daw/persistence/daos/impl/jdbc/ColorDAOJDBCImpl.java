package com.pluralcamp.daw.persistence.daos.impl.jdbc;

import com.mysql.cj.xdevapi.Statement;
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
    private static List<Color> readQuery(ResultSet reader) throws SQLException {

        List<Color> colors = new ArrayList<Color>();

        while (reader.next()) {
            var c = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                    reader.getInt("blue"));
            c.setId(reader.getLong("id"));
            colors.add(c);
        }

        return colors;

    }

    private static String getLastID() throws DAOException {

        try ( // Crear connexion
                Connection conn = createConnexion();
                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall("SELECT id, name, red, green, blue FROM colors ORDER BY ID DESC LIMIT 1");) {

            // Leer consulta
            try (ResultSet reader = sentSQL.executeQuery();) {
                if (reader.next()) {
                    return reader.getString("id");
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
                        .prepareCall("SELECT id, name, red, green, blue FROM colors where id = ?");) {

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

    @Override
    public List<Color> getColors() throws DAOException {

        List<Color> colors = getColors(0, 0);

        return colors;

    }

    @Override
    public List<Color> getColors(int offset, int count) throws DAOException {

        List<Color> colors = new ArrayList<Color>();

        try ( // Crear conexión
                Connection conn = createConnexion();

                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall("SELECT id, name, red, green, blue FROM colors WHERE id > ? AND id <= ?");) {

            // Indicar offset
            sentSQL.setLong(1, offset);

            // Comprobar si no hay límite de filas a devolver
            if (count == 0) {
                String limit = getLastID();
                sentSQL.setString(2, limit);
            } else {
                int limit = offset + count;
                sentSQL.setLong(2, limit);
            }

            // Leer consulta
            ResultSet reader = sentSQL.executeQuery();
            colors = readQuery(reader);

        } catch (SQLException ex) {

            // Logger
            throw new DAOException(ex);

        }

        return colors;

    }

    @Override
    public List<Color> getColors(String searchTerm) throws DAOException {
        return null;
    }

    @Override
    public List<Color> getColors(String searchTerm, int offset, int count) throws DAOException {
        return null;
    }
}
