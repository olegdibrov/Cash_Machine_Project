package com.training.model.dao.impl;

import com.training.model.dao.AbstractDAO;
import com.training.model.entity.Bill;
import com.training.model.entity.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BillDAO extends AbstractDAO<Bill> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Bill bill) {
        try (PreparedStatement st = conn.prepareStatement("INSERT INTO bill (date, id_user, id_bill_status) VALUES (?, ?, ?)")) {
            st.setString(1, bill.getDate());
            st.setInt(2, bill.getIdUser());
            st.setInt(3, bill.getIdBillStatus());
            System.out.println(st);
            st.execute();
        } catch (SQLException | NullPointerException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
//

    /**
     * {@inheritDoc}
     */
    @Override
    public Bill read(Integer id) {
        throw new UnsupportedOperationException();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Bill bill) {
        try (PreparedStatement st = conn.prepareStatement(
                "UPDATE bill" +
                        " SET id_bill = ?, date = ?,  id_bill_statys = ?" +
                        " WHERE id_bill = ?")) {
            st.setInt(1, bill.getIdBill());
            st.setString(2, bill.getDate());
            st.setInt(3, bill.getIdBillStatus());
            st.setInt(4, bill.getIdBill());

            st.execute();
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Bill entity) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bill> getAll() {
        List<Bill> bills = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM bill");
            while (resultSet.next()) {
                bills.add(createBill(resultSet));
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage(), exc);
        }
        return bills;
    }

    private Integer getLastIdBill(Integer id_user) {
        Integer idBill = null;
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT MAX(id_bill) AS 'last_id' FROM bill WHERE id_user = ?")) {
            st.setInt(1, id_user);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                idBill = resultSet.getInt(1);

            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return idBill;

    }


    public Integer createBillAndGetId(Bill bill) {
        Integer id = null;
        setAutoCommit(false);
        try {
            create(bill);
            id = getLastIdBill(bill.getIdUser());
            conn.commit();
        } catch (SQLException | NullPointerException ex) {
            logger.error(ex.getMessage(), ex);
            rollback();
        } finally {
            setAutoCommit(true);
            return id;
        }

    }

    /**
     * creates entity
     *
     * @param resultSet query result set
     * @return entity
     */
    private Bill createBill(ResultSet resultSet) throws SQLException {
        return new Bill(resultSet.getInt("id_bill"),
                resultSet.getString("date"),
                resultSet.getInt("id_user"),
                resultSet.getInt("id_bill_status"),
                new ArrayList<Payment>());
    }
}
