package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.util.ResourceManager;
import com.training.util.constants.PageKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewErrorPage implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("msg", ResourceManager.INSTANCE.getValue("error"));
        req.getRequestDispatcher(PageKey.ERROR_PAGE.toString()).forward(req, resp);
    }
}
