package com.pluralcamp.daw.persistence.exceptions;

public class DAOException extends Exception{

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message) {
        super(message, null);
    }
    
    public DAOException(Throwable cause) {
        this(cause.toString(), cause);
    }

    public DAOException() {
        this("Se ha producido un error en la capa de persistencia", null);
    }

}
