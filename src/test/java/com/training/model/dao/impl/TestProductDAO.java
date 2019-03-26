package com.training.model.dao.impl;

import com.training.model.dao.DAOFactory;
import com.training.model.entity.Product;
import com.training.util.constants.DAOKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestProductDAO {
    private Product testProduct;
    private ProductDAO dao;

    @BeforeEach
    void init() {
        testProduct = new Product();
        testProduct.setName("name");
        testProduct.setDescription("description");
        testProduct.setCost(new Double(150));
        testProduct.setQuantity(50);
        dao = (ProductDAO) DAOFactory.getDAO(DAOKey.PRODUCT_DAO);
    }

    @Test()
    void testCreate() {
        boolean exists = false;
        dao.create(testProduct);
        for (Product product : dao.getAll()) {
            if (product.getName().equals(testProduct.getName())) {
                exists = true;
                testProduct = product;
                break;
            }
        }
        Assertions.assertTrue(exists);

        dao.delete(testProduct);
    }

    @Test
    void testRead() {
        dao.create(testProduct);
        for (Product product : dao.getAll()) {
            if (product.getName().equals(testProduct.getName())) {
                System.out.println(product.getId());
                testProduct = product;
                break;
            }
        }
        System.out.println(testProduct.getId());
        Product product = dao.read(testProduct.getId());
        Assertions.assertEquals(testProduct, product);

        dao.delete(testProduct);
    }

    @Test
    void test_update() {
        dao.create(testProduct);
        for (Product product : dao.getAll()) {
            if (product.getName().equals(testProduct.getName())) {
                testProduct = product;
                break;
            }
        }
        testProduct.setDescription("new description");
        dao.update(testProduct);
        Assertions.assertEquals(testProduct, dao.read(testProduct.getId()));

        dao.delete(testProduct);
    }

    @Test
    void test_delete() {

        dao.create(testProduct);
        for (Product product : dao.getAll()) {
            if (product.getName().equals(testProduct.getName())) {
                testProduct = product;
                break;
            }
        }
        dao.delete(testProduct);
        Assertions.assertNull(dao.read(testProduct.getId()));

        dao.delete(testProduct);
    }

    @Test
    void test_getAll() {
        List<Product> products = dao.getAll();
        Assertions.assertFalse(products.size() <= 1);
    }
}
