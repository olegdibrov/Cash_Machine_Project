package com.training.service.impl;

import com.training.model.dao.DAOFactory;
import com.training.model.dao.impl.PaymentDAO;
import com.training.model.dao.impl.ProductDAO;
import com.training.model.entity.Payment;
import com.training.model.entity.Product;
import com.training.service.Service;
import com.training.util.constants.DAOKey;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PaymentService implements Service {

    protected static Logger logger = LogManager.getLogger(ProductService.class);


    private PaymentDAO paymentDAO;
    private ProductDAO productDAO;

    public PaymentService() {
        this.paymentDAO = (PaymentDAO) DAOFactory.getDAO(DAOKey.PAYMENTS_DAO);
    }

    public void getAllPayments() {
        paymentDAO.getAll();
    }

    public synchronized boolean addProductToBill(Integer idBill,Integer idProduct,Integer quantity) {
        try {
            Payment payment = new Payment();
            payment.setIdBill(idBill);
            payment.setIdProduct(idProduct);
            payment.setQuantity(quantity);
            paymentDAO.create(payment);

            Product product = productDAO.read(idProduct);
            product.setQuantity(product.getQuantity() - quantity);
            productDAO.update(product);
            return true;
        } catch (NullPointerException ex) {
            logger.error(ex);
            return false;
        }
    }
}
