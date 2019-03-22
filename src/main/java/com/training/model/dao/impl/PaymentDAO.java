package com.training.model.dao.impl;

import com.training.model.dao.AbstractDAO;
import com.training.model.entity.Payment;
import com.training.model.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO extends AbstractDAO<Payment> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Payment payment) {
        setAutoCommit(false);
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO payments (id_bill, id_product, quantity) " +
                        "VALUES (?, ?, ?)")) {
            st.setInt(1, payment.getIdBill());
            st.setInt(2, payment.getIdProduct());
            st.setInt(3, payment.getQuantity());
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
    public Payment read(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM payments WHERE id_payments = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return createPayment(resultSet);
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
    public void update(Payment payment) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE payments" +
                        " SET id_bill = ?, id_product = ?, quantity = ?" +
                        " WHERE id_payments = ?")) {
            st.setInt(1, payment.getIdBill());
            st.setInt(2, payment.getIdProduct());
            st.setInt(3, payment.getQuantity());
            st.setInt(4, payment.getIdPayment());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Payment payment) {
        try (PreparedStatement st = conn.prepareStatement(
                "DELETE FROM payments WHERE id_payments = ?")) {
            st.setInt(1, payment.getIdPayment());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc);
        }

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Payment> getAll() {
        List<Payment> list = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM payments");

            while (resultSet.next()) {
                list.add(createPayment(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
        return list;
    }

    public void createPaymentAndChangeQuantity(Payment payment) {
        create(payment);


    }

    /**
     * creates entity
     *
     * @param resultSet query result set
     * @return entity
     */
    private Payment createPayment(ResultSet resultSet) throws SQLException {
        return new Payment(resultSet.getInt("id_bill"),
                resultSet.getInt("id_payments"),
                resultSet.getInt("id_product"),
                resultSet.getInt("quantity"));
    }
}
