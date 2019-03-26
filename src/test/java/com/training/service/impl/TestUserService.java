package com.training.service.impl;

import com.training.model.dao.impl.UserDAO;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.util.constants.ServiceKey;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TestUserService {

    private UserService userService;
    PasswordEncryptor bpe;
    private User testUser;

    @BeforeEach
    void init() {
        userService = (UserService) ServiceFactory.getService(ServiceKey.USER_SERVICE);
        bpe = new BasicPasswordEncryptor();
        testUser = new User();
        testUser.setLogin("login");
        testUser.setPassword("password");
        testUser.setRole(1);
    }

    @Test
    void register() {
        userService.createUser(testUser.getLogin(), testUser.getPassword(), testUser.getRole());
        User user = userService.getUser(testUser.getLogin());
        Assertions.assertTrue((testUser.getLogin().equals(user.getLogin())));

        userService.userDAO.delete(user);

    }

}
