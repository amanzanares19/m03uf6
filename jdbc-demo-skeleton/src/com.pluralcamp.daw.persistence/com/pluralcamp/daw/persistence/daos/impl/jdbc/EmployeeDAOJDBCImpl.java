package com.pluralcamp.daw.persistence.daos.impl.jdbc;

import com.pluralcamp.daw.entities.core.Employee;
import com.pluralcamp.daw.entities.core.Employee.Gender;
import com.pluralcamp.daw.persistence.daos.contracts.EmployeeDAO;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOJDBCImpl implements EmployeeDAO {

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
    private List<Employee> readQuery(ResultSet reader) throws SQLException {

        List<Employee> employees = new ArrayList<Employee>();

        // Si la query no retorna res
        if (!reader.isBeforeFirst()) {
            throw new SQLException("No records found in the table.");
        }

        while (reader.next()) {

            // Gender
            Gender gender = reader.getString("gender") == "MALE" ? Gender.MALE : Gender.FEMALE;

            LocalDate birthDate = reader.getDate("birthDate").toLocalDate();
            LocalDate hireDate = reader.getDate("hireDate").toLocalDate();

            var em = new Employee(reader.getString("code"), reader.getString("firstname"),
                    reader.getString("lastname"),
                    gender, birthDate, hireDate, reader.getDouble("monthlySalary"), reader.getInt("payments"));
            em.setId(reader.getLong("id"));
            employees.add(em);

        }

        return employees;

    }

    private static int getLastID() throws DAOException {

        try ( // Crear connexion
                Connection conn = createConnexion();
                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall("CALL getLastEmployee()");) {

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
    public Employee getEmployeeById(long id) throws DAOException {

        Employee em = null;

        try ( // Crear connexion
                Connection conn = createConnexion();
                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall(
                                "CALL getEmployeeById(?)");) {

            sentSQL.setLong(1, id);

            // Leer consulta
            try (ResultSet reader = sentSQL.executeQuery();) {
                if (reader.next()) {

                    // Convertir fechas
                    LocalDate birthDate = reader.getDate("birthDate").toLocalDate();
                    LocalDate hireDate = reader.getDate("hireDate").toLocalDate();

                    em = new Employee(reader.getString("code"), reader.getString("firstname"),
                            reader.getString("lastname"), Gender.valueOf(reader.getString("gender").trim()),
                            birthDate, hireDate, reader.getDouble("monthlySalary"), reader.getInt("payments"));
                    em.setId(reader.getLong("id"));
                }
            }

        } catch (SQLException ex) {

            // Logger
            throw new DAOException(ex);

        }

        return em;

    }

    @Override
    public List<Employee> getEmployees() throws DAOException {
        List<Employee> employees = getEmployees(DEFAULT_VALUE, DEFAULT_VALUE);

        return employees;
    }

    @Override
    public List<Employee> getEmployees(int offset, int count) throws DAOException {
        List<Employee> employees = getEmployees(EMPTY, offset, count);

        return employees;
    }

    @Override
    public List<Employee> getEmployees(String searchTerm) throws DAOException {
        List<Employee> employees = getEmployees(searchTerm, DEFAULT_VALUE, DEFAULT_VALUE);

        return employees;
    }

    @Override
    public List<Employee> getEmployees(String searchTerm, int offset, int count) throws DAOException {

        List<Employee> employees = new ArrayList<Employee>();

        try ( // Crear conexión
                Connection conn = createConnexion();

                // Crear consulta
                CallableStatement sentSQL = conn
                        .prepareCall(
                                "CALL getEmployees(?,?,?)");) {

            // filter
            sentSQL.setString(1, "%" + searchTerm.trim() + "%");
            sentSQL.setInt(2, offset);

            // if count is 0, then the limit is the last row of the table
            if (count == 0) {
                count = getLastID();
            }
            sentSQL.setInt(3, count);

            // Leer consulta
            ResultSet reader = sentSQL.executeQuery();
            employees = this.readQuery(reader);

        } catch (SQLException ex) {

            // Logger
            throw new DAOException(ex);

        }

        return employees;

    }
}
