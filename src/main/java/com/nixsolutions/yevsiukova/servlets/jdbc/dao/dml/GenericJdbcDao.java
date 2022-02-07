package com.nixsolutions.yevsiukova.servlets.jdbc.dao.dml;

import com.nixsolutions.yevsiukova.servlets.jdbc.dao.Dao;
import com.nixsolutions.yevsiukova.servlets.jdbc.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


public abstract class GenericJdbcDao<E> implements Dao<E> {

    public void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    public void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    public void connectionRollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }

        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

}
