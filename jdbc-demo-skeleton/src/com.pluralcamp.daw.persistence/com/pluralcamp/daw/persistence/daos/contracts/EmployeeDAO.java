package com.pluralcamp.daw.persistence.daos.contracts;

import com.pluralcamp.daw.entities.core.Employee;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

import java.util.List;

public interface EmployeeDAO {
    Employee getEmployeeById(long id) throws DAOException;
    List<Employee> getEmployees() throws DAOException;
    List<Employee> getEmployees(int offset, int count) throws DAOException;
    List<Employee> getEmployees(String searchTerm) throws DAOException;
    List<Employee> getEmployees(String searchTerm, int offset, int count) throws DAOException;
}
