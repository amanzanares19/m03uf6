package com.pluralcamp.daw.persistence.daos.impl.jdbc;

import com.pluralcamp.daw.entities.core.Event;
import com.pluralcamp.daw.persistence.daos.contracts.EventDAO;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

import java.util.List;

public class EventDAOJDBCImpl implements EventDAO {
    @Override
    public Event getEventById(long id) throws DAOException {
        return null;
    }

    @Override
    public List<Event> getEvents() throws DAOException {
        return null;
    }

    @Override
    public List<Event> getEvents(int offset, int count) throws DAOException {
        return null;
    }

    @Override
    public List<Event> getEvents(String searchTerm) throws DAOException {
        return null;
    }

    @Override
    public List<Event> getEvents(String searchTerm, int offset, int count) throws DAOException {
        return null;
    }
}
