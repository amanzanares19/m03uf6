package com.pluralcamp.daw.persistence.daos.impl.jdbc;

import com.pluralcamp.daw.entities.core.Employee;
import com.pluralcamp.daw.persistence.daos.contracts.EmployeeDAO;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

import java.util.List;

public class EmployeeDAOJDBCImpl implements EmployeeDAO {
    @Override
    public Employee getEmployeeById(long id) throws DAOException {
        return null;
    }

    @Override
    public List<Employee> getEmployees() throws DAOException {
        return null;
    }

    @Override
    public List<Employee> getEmployees(int offset, int count) throws DAOException {
        return null;
    }

    @Override
    public List<Employee> getEmployees(String searchTerm) throws DAOException {
        return null;
    }

    @Override
    public List<Employee> getEmployees(String searchTerm, int offset, int count) throws DAOException {
        return null;
    }
}
