package com.training.controller;

import com.training.controller.command.Command;
import com.training.controller.command.CommandFactory;
import com.training.util.CommandManager;
import com.training.util.constants.CommandKey;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Controller", urlPatterns = "/home")
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger("Controller.class");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * defines command and executes it
     *
     * @param request  request from client to server
     * @param response response from server to client
     * @throws ServletException     if ServletException occurs
     * @throws IOException          if IOException occurs
     * @throws NullPointerException if NullPointer occurs
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Command command = CommandFactory.getCommand(request);
            command.execute(request, response);
        } catch (NullPointerException | IllegalArgumentException | ServletException | IOException exc) {
            logger.error(exc.getMessage(), exc);
            response.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_ERROR_PAGE.toString()));
            exc.printStackTrace();
        }
    }
}
