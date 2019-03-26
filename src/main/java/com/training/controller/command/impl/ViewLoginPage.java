package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.UserService;
import com.training.util.CommandManager;
import com.training.util.ResourceManager;
import com.training.util.constants.CommandKey;
import com.training.util.constants.PageKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewLoginPage implements Command {

    /**
     * user service field
     */
    UserService userService = (UserService) ServiceFactory.getService(ServiceKey.USER_SERVICE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            login(req, resp);
        } else {
            req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString()).forward(req, resp);
        }
    }

    /**
     * reads passed parameters and performs login procedures
     *
     * @param req  request from client to server
     * @param resp response from server to client
     * @throws ServletException if ServletException occurs
     * @throws IOException      if IOException occurs
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = userService.getUser(req.getParameter("Login"));
        boolean isValid = user != null && userService.isValid(user, req.getParameter("Password"));

        if (isValid) {
            req.getSession().setAttribute("user", user);

            switch (user.getRole()) {
                case 1:
                    req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString()).forward(req, resp);
                    break;
                case 2:
                    resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_HOME_PAGE.toString()));
                    break;
                case 3:
                    resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_HOME_PAGE.toString()));
                    break;
                case 4:
                    resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_STOCK_PAGE.toString()));
                    break;
                default:
                    req.setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectCredentials"));
                    req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString()).forward(req, resp);
            }
        } else {
            req.setAttribute("msg", ResourceManager.INSTANCE.getValue("incorrectCredentials"));
            req.getRequestDispatcher(PageKey.LOGIN_PAGE.toString()).forward(req, resp);
        }

    }
}
