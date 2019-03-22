package com.training.model.dao.impl;

import com.training.model.dao.AbstractDAO;
import com.training.model.entity.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides operations for working with user_role table in database
 *
 * @author Oleg Dibrov
 */

public class UserRoleDAO extends AbstractDAO<UserRole> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void create(UserRole userRole) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO users_roles VALUES (?, ?)")) {
            st.setInt(1, userRole.getId());
            st.setString(2, userRole.getRoleName());

            st.execute();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRole read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM users_roles WHERE id_users_roles = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(UserRole userRole) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE users_roles" +
                        " SET role_name = ?" +
                        " WHERE id_users_roles = ?")) {
            st.setString(1, userRole.getRoleName());
            st.setInt(2, userRole.getId());

            st.execute();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(UserRole userRole) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM users_roles WHERE id_users_roles = ?")) {
            st.setInt(1, userRole.getId());

            st.execute();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserRole> getAll() {
        List<UserRole> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery(
                    "SELECT * FROM users_roles");

            while (resultSet.next()) {
                list.add(createUser(resultSet));
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return list;
    }

    /**
     * creates entity
     *
     * @param resultSet query result set
     * @return entity
     */
    private UserRole createUser(ResultSet resultSet) throws SQLException {
        return new UserRole(
                resultSet.getInt("id_users_roles"),
                resultSet.getString("role_name"));
    }

}
