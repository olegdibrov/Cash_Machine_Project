package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.util.CommandManager;
import com.training.util.constants.CommandKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CloseBill implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("id_bill");
        session.removeAttribute("paymentStatus");
        resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_HOME_PAGE.toString()));
    }
}
