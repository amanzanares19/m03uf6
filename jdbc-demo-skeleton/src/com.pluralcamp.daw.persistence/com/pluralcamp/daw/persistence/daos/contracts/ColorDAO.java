package com.pluralcamp.daw.persistence.daos.contracts;

import com.pluralcamp.daw.entities.core.Color;
import com.pluralcamp.daw.persistence.exceptions.DAOException;

import java.util.List;

public interface ColorDAO {
    Color getColorById(long id) throws DAOException;
    List<Color> getColors() throws DAOException;
    List<Color> getColors(int offset, int count) throws DAOException;
    List<Color> getColors(String searchTerm) throws DAOException;
    List<Color> getColors(String searchTerm, int offset, int count) throws DAOException;
}
