package com.nixsolutions.yevsiukova.servlets.jdbc.sql;

public class SQLRequests {
    public static final String CREATE_ROLE = "INSERT INTO roles (roleName) VALUES (?)";
    public static final String UPDATE_ROLE = "UPDATE roles SET roleName=?";
    public static final String DELETE_ROLE = "DELETE FROM roles WHERE roleId=?";
    public static final String FIND_BY_ROLE_ID = "SELECT * FROM roles WHERE roleId=?";
    public static final String FIND_BY_NAME = "SELECT * FROM roles WHERE roleName=?";
    public static final String FIND_ALL_ROLES = "SELECT * FROM roles";

    //----------------------------------------USERS-----------------------------------------//
    public static final String FIND_ALL_USERS = "SELECT * FROM users";
    public static final String CREATE_USER = "INSERT INTO users (roleId, login, password, email, firstName, " +
            "lastName, birthday) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_USER = "UPDATE users set roleId = ?, login = ?, " +
            "password = ?, email = ?, firstName = ?, lastName = ?, birthday = ? WHERE email = ?";
    public static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    public static final String FIND_BY_ID = "SELECT * FROM users WHERE id=?";
    public static final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id=?";

    //------------------------for servlets
    public static final String UPDATE_USER_LOGIN = "UPDATE users set roleId = ?, login = ?, " +
            "password = ?, email = ?, firstName = ?, lastName = ?, birthday = ? WHERE login = ?";
}
