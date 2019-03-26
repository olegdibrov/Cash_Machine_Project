package com.training.model.dao.impl;

import com.training.model.dao.DAOFactory;
import com.training.model.entity.User;
import com.training.util.constants.DAOKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestUserDAO {
    private User testUser;
    private UserDAO dao;

    @BeforeEach
    void init() {
        testUser = new User();
        testUser.setLogin("login");
        testUser.setPassword("password");
        testUser.setRole(1);
        dao = (UserDAO) DAOFactory.getDAO(DAOKey.USER_DAO);
    }

    @Test
    void testCreate() {
        boolean exists = false;

        dao.create(testUser);
        for (User user : dao.getAll()) {
            if (user.getLogin().equals(testUser.getLogin())) {
                exists = true;
                testUser = user;
                break;
            }
        }
        Assertions.assertTrue(exists);

        dao.delete(testUser);
    }

    @Test
    void testRead() {
        dao.create(testUser);
        for (User user : dao.getAll()) {
            if (user.getLogin().equals(testUser.getLogin())) {
                System.out.println(user.getId());
                testUser = user;
                break;
            }
        }
        System.out.println(testUser.getId());
        User user = dao.read(testUser.getId());
        Assertions.assertEquals(testUser, user);

        dao.delete(testUser);
    }

    @Test
    void testUpdate() {
        dao.create(testUser);
        for (User user : dao.getAll()) {
            if (user.getLogin().equals(testUser.getLogin())) {
                testUser = user;
                break;
            }
        }
        testUser.setRole(2);
        dao.update(testUser);
        Assertions.assertEquals(testUser, dao.read(testUser.getId()));

        dao.delete(testUser);
    }

    @Test
    void testDelete() {
        dao.create(testUser);
        for (User user : dao.getAll()) {
            if (user.getLogin().equals(testUser.getLogin())) {
                testUser = user;
                break;
            }
        }
        dao.delete(testUser);
        Assertions.assertNull(dao.read(testUser.getId()));
    }

    @Test
    void testGetAll() {
        List<User> users = dao.getAll();
        Assertions.assertFalse(users.size() <= 1);
    }
}
