package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.util.CommandManager;
import com.training.util.constants.CommandKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        session.removeAttribute("user");
        session.invalidate();

        resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_HOME_PAGE.toString()));
    }
}
