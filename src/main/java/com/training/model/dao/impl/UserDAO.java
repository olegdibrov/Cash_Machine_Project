package com.training.model.dao.impl;

import com.training.model.dao.AbstractDAO;
import com.training.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void create(User user) {
        setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO users (user_login, user_password, id_users_roles) " +
                        "VALUES (?, ?, ?)")) {
            System.out.println(user);
            st.setString(1, user.getLogin());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getRole());
            System.out.println(st);
            st.execute();
            conn.commit();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            rollback();
        } finally {
            setAutoCommit(true);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public User read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM users WHERE id_user = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User entity) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(User entity) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM users");

            while (resultSet.next()) {
                list.add(createUser(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
        System.out.println(list);
        return list;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id_user"),
                resultSet.getString("user_login"),
                resultSet.getString("user_password"),
                resultSet.getInt("id_users_roles"));
    }
}
