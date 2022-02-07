package com.nixsolutions.yevsiukova.servlets.jdbc.dao.dml;

import com.nixsolutions.yevsiukova.servlets.jdbc.connection.Singleton;
import com.nixsolutions.yevsiukova.servlets.jdbc.dao.UserDao;
import com.nixsolutions.yevsiukova.servlets.jdbc.dao.exception.DaoException;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.UserEntity;
import com.nixsolutions.yevsiukova.servlets.jdbc.sql.SQLRequests;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Statement;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao extends GenericJdbcDao<UserEntity> implements UserDao {
    private static final Logger LOG = LogManager.getLogger(JdbcUserDao.class);

    @Override
    public void create(UserEntity userEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Singleton.getConnection();
            connection.setAutoCommit(false);
            preparedStatement =
                    connection.prepareStatement(SQLRequests.CREATE_USER);
            setUserPreparedStatementForCreate(userEntity, preparedStatement);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException exception) {
            connectionRollback(connection);
            throw new DaoException(exception);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void update(UserEntity userEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Singleton.getConnection();
            connection.setAutoCommit(false);
            preparedStatement =
                    connection.prepareStatement(SQLRequests.UPDATE_USER_LOGIN);
            setUserPreparedStatementForUpdate(userEntity, preparedStatement);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException exception) {
            connectionRollback(connection);
            throw new DaoException(exception);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void remove(UserEntity userEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Singleton.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQLRequests.DELETE_USER);
            preparedStatement.setLong(1, userEntity.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException exception) {
            connectionRollback(connection);
            throw new DaoException(exception);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public UserEntity findById(Long id) {
        UserEntity user = new UserEntity();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Singleton.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequests.FIND_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                getUserCellValues(user, resultSet);
            }
        } catch (SQLException e) {
            connectionRollback(connection);
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        LOG.debug(user);
        return user;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return findUserByParameter(email, SQLRequests.FIND_BY_EMAIL);
    }

    @Override
    public UserEntity findByLogin(String login) {
        return findUserByParameter(login, SQLRequests.FIND_BY_LOGIN);
    }

    @Override
    public List<UserEntity> findAll() {
        Connection connection = null;
        List<UserEntity> userList = new ArrayList<>();
        try {
            connection = Singleton.getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLRequests.FIND_ALL_USERS);
            while (resultSet.next()) {
                UserEntity user = new UserEntity();
                getUserCellValues(user, resultSet);
                userList.add(user);
            }
            connection.commit();

        } catch (SQLException exception) {
            connectionRollback(connection);
            throw new DaoException(exception);
        } finally {
            closeConnection(connection);
        }
        LOG.debug(userList);
        return userList;
    }

    private void getUserCellValues(UserEntity user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getLong("id"));
        user.setRoleId(resultSet.getLong("roleId"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setBirthday(resultSet.getDate("birthday"));
    }

    private void setUserPreparedStatementForUpdate(UserEntity userEntity, PreparedStatement preparedStatement) throws SQLException {
        setUserPreparedStatement(userEntity, preparedStatement);
        preparedStatement.setString(8, userEntity.getEmail());
    }

    private void setUserPreparedStatement(UserEntity userEntity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, userEntity.getRoleId());
        preparedStatement.setString(2, userEntity.getLogin());
        preparedStatement.setString(3, userEntity.getPassword());
        preparedStatement.setString(4, userEntity.getEmail());
        preparedStatement.setString(5, userEntity.getFirstName());
        preparedStatement.setString(6, userEntity.getLastName());
        preparedStatement.setDate(7, (Date) userEntity.getBirthday());
    }

    private void setUserPreparedStatementForCreate(UserEntity userEntity, PreparedStatement preparedStatement) throws SQLException {
        setUserPreparedStatement(userEntity, preparedStatement);
    }

    private UserEntity findUserByParameter(String email, String findByEmail) {
        UserEntity user = new UserEntity();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = Singleton.getConnection();
            preparedStatement = connection.prepareStatement(findByEmail);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                getUserCellValues(user, resultSet);
            }
        } catch (SQLException e) {
            connectionRollback(connection);
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        LOG.debug(user);
        return user;
    }


}
