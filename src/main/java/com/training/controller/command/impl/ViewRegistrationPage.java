package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.service.ServiceFactory;
import com.training.service.impl.UserService;
import com.training.util.ResourceManager;
import com.training.util.constants.PageKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewRegistrationPage implements Command {

    /**
     * User service field
     */
    UserService userService = (UserService) ServiceFactory.getService(ServiceKey.USER_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            register(req, resp);
        } else {
            req.getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString()).forward(req, resp);
        }

    }

    /**
     * reads passed parameters and performs registration procedures
     *
     * @param req  request from client to server
     * @param resp response from server to client
     * @throws ServletException if ServletException occurs
     * @throws IOException      if IOException occurs
     */
    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("Login");
        String pass = req.getParameter("Password");
        String passRep = req.getParameter("Re-Password");
        String role = req.getParameter("role");

        boolean isValid = userService.validateLoginInput(login) &&
                userService.validatePasswordInput(pass, passRep) &&
                userService.getUser(login) == null && role != null;

        if (isValid) {
            Integer roleId = Integer.parseInt(role);
            System.out.println(isValid);
            userService.createUser(login, pass, roleId);
            req.setAttribute("msg", ResourceManager.INSTANCE.getValue("registrationSuccess"));
            req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString()).forward(req, resp);
        } else {
            req.setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectCredentials"));
            req.getRequestDispatcher(PageKey.REGISTRATION_PAGE.toString()).forward(req, resp);
        }
    }
}
