package com.training.model.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Class provides connection pool in database
 *
 * @author Oleg Dibrov
 */
public class ConnectionPool {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);

    /**
     * /**
     * datasource field
     */
    private static volatile DataSource dataSource;

    public ConnectionPool() {
    }

    /**
     * @return {@link #dataSource}
     */
    private static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ResourceBundle resource = ResourceBundle.getBundle("database");
                    ds.setDriverClassName(resource.getString("driver"));
                    ds.setUrl(resource.getString("url"));
                    ds.setUsername(resource.getString("user"));
                    ds.setPassword(resource.getString("password"));
                    ds.setMinIdle(Integer.valueOf(resource.getString("min")));
                    ds.setMaxIdle(Integer.valueOf(resource.getString("max")));
                    ds.setMaxOpenPreparedStatements(Integer.valueOf(resource.getString("statements")));
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
            throw new NullPointerException();
        }
    }
}
