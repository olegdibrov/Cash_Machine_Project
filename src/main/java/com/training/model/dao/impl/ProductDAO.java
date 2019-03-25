package com.training.model.dao.impl;

import com.training.model.dao.AbstractDAO;
import com.training.model.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Product product) {
        setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO products (product_name, product_description, product_cost, product_quantity) " +
                        "VALUES (?, ?, ?, ?)")) {
            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setDouble(3, product.getCost());
            st.setInt(4, product.getQuantity());
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
    public Product read(Integer id) {
        setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM products WHERE id_product = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createProduct(resultSet);
            }
            conn.commit();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            rollback();
        } finally {
            setAutoCommit(true);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Product product) {
        setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE products" +
                        " SET product_name = ?, product_description = ?, product_cost = ?, product_quantity = ?" +
                        " WHERE id_product = ?")) {
            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setDouble(3, product.getCost());
            st.setInt(4, product.getQuantity());
            st.setInt(5, product.getId());
            System.out.println(st);
            st.execute();
            conn.commit();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            rollback();
            ex.printStackTrace();
        } finally {
            setAutoCommit(true);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Product product) {
        setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM products WHERE id_product = ?")) {
            st.setInt(1, product.getId());
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
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();

        setAutoCommit(false);
        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                list.add(createProduct(resultSet));
            }
            conn.commit();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            rollback();
        } finally {
            setAutoCommit(true);
        }
        return list;
    }

    /**
     * creates entity
     *
     * @param resultSet query result set
     * @return entity
     */
    private Product createProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getInt("id_product"),
                resultSet.getString("product_name"),
                resultSet.getString("product_description"),
                resultSet.getDouble("product_cost"),
                resultSet.getInt("product_quantity"));
    }


}
