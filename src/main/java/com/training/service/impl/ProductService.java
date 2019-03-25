package com.training.service.impl;

import com.training.model.dao.DAOFactory;
import com.training.model.dao.impl.ProductDAO;
import com.training.model.entity.Product;
import com.training.service.Service;
import com.training.util.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.training.util.constants.DAOKey;

import java.sql.SQLException;
import java.util.List;

public class ProductService implements Service {
    protected static Logger logger = LogManager.getLogger(ProductService.class);


    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = (ProductDAO) DAOFactory.getDAO(DAOKey.PRODUCT_DAO);
    }

    public void createProduct(String name, String description, Double cost, Integer quantity) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCost(cost);
        product.setQuantity(quantity);
        productDAO.create(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    public Product getProduct(Integer id) {
        return productDAO.read(id);
    }

    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    public boolean exist(Integer idProduct, Integer quantity) {
        Product product =  productDAO.read(idProduct);
        if (product != null) {
            if (product.getQuantity() >= quantity) {
                return true;
            }
        }
        return false;
    }

    public boolean validateString(String string) {
        return Validator.validateString(string);
    }
}
