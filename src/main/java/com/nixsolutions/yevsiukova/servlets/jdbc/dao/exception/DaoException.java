package com.nixsolutions.yevsiukova.servlets.jdbc.dao.exception;

public class DaoException extends RuntimeException{

    public DaoException(Throwable exception) {
        super(exception);
    }
}
