package com.training.model.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


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
                    System.out.println("TRYYYYY");
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
                    ds.setUrl("jdbc:mysql://localhost:3306/cash_machine_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
                    ds.setUsername("root");
                    ds.setPassword("password");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
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
