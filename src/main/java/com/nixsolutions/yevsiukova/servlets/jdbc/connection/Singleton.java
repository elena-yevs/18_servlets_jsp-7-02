package com.nixsolutions.yevsiukova.servlets.jdbc.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.Properties;

public class Singleton {
    private static final Logger LOG = LogManager.getLogger(Singleton.class);
    private static final String DB_DRIVER = "db.driver.class";
    private static final String DB_URL = "db.conn.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private static BasicDataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = Singleton.class.getClassLoader().getResourceAsStream("jdbc.properties");

            properties.load(inputStream);
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty(DB_DRIVER));
            dataSource.setUrl(properties.getProperty(DB_URL));
            dataSource.setUsername(properties.getProperty(DB_USERNAME));
            dataSource.setPassword(properties.getProperty(DB_PASSWORD));
        } catch (IOException ioException) {
            LOG.error(ioException);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}