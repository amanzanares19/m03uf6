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
    @Override
    public Color getColorById(long id) throws DAOException {

        // Objectes que calen:
        // 1er objecte - Connexio, via DriverManager de JDBC
        // 2n objecte - Obrir un canal de Consulta - PraparedStatement
        // 2.1 - Enviar la consulta SQL
        // 3er objecte - Obrir un canal de Lectura, un cursor - ResultSet
        Color c = null;

        try (// Crear connexion
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "alex", "12345678");
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

        List<Color> colors = new ArrayList<Color>();

        try (   // Crear connexion
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/calendar-restore?serverTimezone=Europe/Paris", "alex", "12345678");
                // Crear consulta
                CallableStatement sentSQL = conn.prepareCall("SELECT id, name, red, green, blue FROM colors");
                // Leer consulta
                ResultSet reader = sentSQL.executeQuery();) {

            while (reader.next()) {
                var c = new Color(reader.getString("name"), reader.getInt("red"), reader.getInt("green"),
                        reader.getInt("blue"));
                c.setId(reader.getLong("id"));
                colors.add(c);
            }

        } catch (SQLException ex) {

            // Logger
            throw new DAOException(ex);

        }

        return colors;

    }

    @Override
    public List<Color> getColors(int offset, int count) throws DAOException {
        return null;
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
