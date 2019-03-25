package com.training.service.impl;

import com.training.model.dao.DAOFactory;
import com.training.model.dao.impl.PaymentDAO;
import com.training.model.dao.impl.ProductDAO;
import com.training.model.entity.Payment;
import com.training.model.entity.PaymentPreview;
import com.training.model.entity.Product;
import com.training.service.Service;
import com.training.util.constants.DAOKey;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PaymentService implements Service {

    protected static Logger logger = LogManager.getLogger(ProductService.class);


    private PaymentDAO paymentDAO;
    private ProductDAO productDAO;

    public PaymentService() {
        this.paymentDAO = (PaymentDAO) DAOFactory.getDAO(DAOKey.PAYMENTS_DAO);
        this.productDAO = (ProductDAO) DAOFactory.getDAO(DAOKey.PRODUCT_DAO);
    }

    public void getAllPayments() {
        paymentDAO.getAll();
    }

    public boolean removeProductFromBill(Integer idPayment) {
        Payment payment = paymentDAO.read(idPayment);
        if (payment != null) {
            paymentDAO.delete(payment);
            return true;

        } else return false;
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

    public List<PaymentPreview> findProductsByIdBill(Integer idBill) {
        List<PaymentPreview> paymentPreview = new ArrayList<>();
        if (idBill != null) {
            List<Payment> payments = paymentDAO.getListOfPayment(idBill);
            for (Payment payment: payments) {
                PaymentPreview preview = new PaymentPreview();
                preview.setIdPayment(payment.getIdPayment());
                preview.setQuantity(payment.getQuantity());
                preview.setProduct(productDAO.read(payment.getIdProduct()));
                paymentPreview.add(preview);
            }
        }
        return paymentPreview;
    }


}
