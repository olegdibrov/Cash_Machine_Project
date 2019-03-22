package com.training.service.impl;

import com.training.model.dao.DAOFactory;
import com.training.model.dao.impl.BillDAO;
import com.training.model.dao.impl.PaymentDAO;
import com.training.model.dao.impl.ProductDAO;
import com.training.service.Service;
import com.training.util.constants.DAOKey;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TransactionService implements Service {
    protected static Logger logger = LogManager.getLogger(TransactionService.class);

    private ProductDAO productDAO;
    private PaymentDAO paymentDAO;
    private BillDAO billDAO;


    public TransactionService() {
        this.productDAO = (ProductDAO) DAOFactory.getDAO(DAOKey.PRODUCT_DAO);
    }


}
