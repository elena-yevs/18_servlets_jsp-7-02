package com.nixsolutions.yevsiukova.servlets.jdbc.dao.dml;

import com.nixsolutions.yevsiukova.servlets.jdbc.connection.Singleton;
import com.nixsolutions.yevsiukova.servlets.jdbc.dao.RoleDao;
import com.nixsolutions.yevsiukova.servlets.jdbc.dao.exception.DaoException;
import com.nixsolutions.yevsiukova.servlets.jdbc.entity.RoleEntity;
import com.nixsolutions.yevsiukova.servlets.jdbc.sql.SQLRequests;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcRoleDao extends GenericJdbcDao<RoleEntity> implements RoleDao {
    private static final Logger LOG = LogManager.getLogger(JdbcRoleDao.class);

    @Override
    public void create(RoleEntity roleEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Singleton.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQLRequests.CREATE_ROLE);
            preparedStatement.setString(1, roleEntity.getRoleName());
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
    public void update(RoleEntity roleEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Singleton.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQLRequests.UPDATE_ROLE);
            preparedStatement.setString(1, roleEntity.getRoleName());
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
    public void remove(RoleEntity roleEntity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Singleton.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQLRequests.DELETE_ROLE);
            preparedStatement.setLong(1, roleEntity.getRoleId());
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
    public RoleEntity findByName(String name) {
        RoleEntity role = new RoleEntity();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = Singleton.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequests.FIND_BY_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role.setRoleId(resultSet.getLong(1));
                role.setRoleName(resultSet.getString(2));
            }
            connection.commit();

        } catch (SQLException exception) {
            connectionRollback(connection);
            throw new DaoException(exception);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        LOG.debug(role);
        return role;
    }


    @Override
    public List<RoleEntity> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<RoleEntity> roleList = new ArrayList<>();
        try {
            connection = Singleton.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLRequests.FIND_ALL_ROLES);
            while (resultSet.next()) {
                RoleEntity role = new RoleEntity();
                role.setRoleId(resultSet.getLong("roleId"));
                role.setRoleName(resultSet.getString("roleName"));
                roleList.add(role);
            }
            connection.commit();

        } catch (SQLException exception) {
            connectionRollback(connection);
            throw new DaoException(exception);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
            closeResultSet(resultSet);
        }
        LOG.debug(roleList);
        return roleList;
    }

    @Override
    public RoleEntity findById(Long id) {
        RoleEntity role = new RoleEntity();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        try {
            connection = Singleton.getConnection();
            preparedStatement = connection.prepareStatement(SQLRequests.FIND_BY_ROLE_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role.setRoleId(resultSet.getLong(1));
                role.setRoleName(resultSet.getString(2));
            }
            connection.commit();
        } catch (SQLException exception) {
            connectionRollback(connection);
            throw new DaoException(exception);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        LOG.debug(role);
        return role;
    }

}
