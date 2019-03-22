package com.training.controller.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface providing execute method
 *
 * @author Oleg Dibrov
 */
public interface Command {

    /**
     * gets http request and http response
     *
     * @param req  request from client to server
     * @param resp response from server to client
     * @throws ServletException if ServletException occurs
     * @throws IOException      if IOException occurs
     */
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
