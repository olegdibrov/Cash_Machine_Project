package com.training.controller.command;

import com.training.controller.command.impl.*;
import com.training.util.constants.CommandKey;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.training.util.constants.CommandKey.*;

public class CommandFactory {
    /**
     * map for instances
     */
    private static Map<CommandKey, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put(COMMAND_VIEW_HOME_PAGE, new ViewHomePage());
        commands.put(COMMAND_VIEW_REGISTRATION_PAGE, new ViewRegistrationPage());
        commands.put(COMMAND_CHANGE_LOCALIZATION, new ChangeLocalization());
        commands.put(COMMAND_VIEW_ERROR_PAGE, new ViewErrorPage());
        commands.put(COMMAND_VIEW_LOGIN_PAGE, new ViewLoginPage());
        commands.put(COMMAND_LOGOUT, new Logout());
        commands.put(COMMAND_VIEW_BILL_PAGE, new ViewBillPage());
        commands.put(COMMAND_ADD_PRODUCTS_TO_BILL, new AddProductsToBill());
    }

    /**
     * defines command
     *
     * @param req request from client
     * @return command instance
     */
    public static Command getCommand(HttpServletRequest req) {
        String id = req.getParameter("command");
        if (id == null) {
            return commands.get(COMMAND_VIEW_HOME_PAGE);
        }
        return commands.get(CommandKey.get(id));
    }
}
