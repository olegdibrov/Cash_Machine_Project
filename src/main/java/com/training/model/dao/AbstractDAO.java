package com.training.model.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract class provides methods for access to database data.
 *
 * @author Oleg Dibrov
 */

public abstract class AbstractDAO<T> {

    /**
     * connection field
     */
    protected Connection conn;

    /**
     * logger field
     */
    protected static Logger logger = LogManager.getLogger(AbstractDAO.class);

    /**
     * constructor without parameters
     */
    protected AbstractDAO() {
        conn = ConnectionPool.getConnection();
    }

    /**
     * adds entity to database
     *
     * @param entity creating entity
     */
    public abstract void create(T entity);

    /**
     * gets entity from database
     *
     * @param id id of entity
     * @return found entity
     */
    public abstract T read(Integer id);

    /**
     * updates entity in database
     *
     * @param entity entity for update
     */
    public abstract void update(T entity);

    /**
     * deletes entity from database
     *
     * @param entity entity to delete
     */
    public abstract void delete(T entity);

    /**
     * gets all entities from database
     *
     * @return List of all entities
     */
    public abstract List<T> getAll();

    protected void setAutoCommit(boolean b) {
        try {
            conn.setAutoCommit(b);
        } catch (SQLException exc) {
            logger.error("Exception while changing autocommit status", exc);
        }
    }

    protected void rollback() {
        try {
            conn.rollback();
        } catch (SQLException exc) {
            logger.error("Exception while rollback connection", exc);
        }
    }


}
