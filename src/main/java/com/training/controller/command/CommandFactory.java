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
        commands.put(COMMAND_UPDATE_PRODUCT_INFO, new UpdateProductInfo());
        commands.put(COMMAND_VIEW_STOCK_PAGE, new ViewStockPage());
        commands.put(COMMAND_CREATE_PRODUCT, new CreateProduct());
        commands.put(COMMAND_VIEW_PRODUCTS_IN_BILL, new ViewProductsInBill());
        commands.put(COMMAND_CLOSE_BILL, new CloseBill());
        commands.put(COMMAND_REMOVE_PRODUCT_FROM_BILL, new RemoveProductFromBill());
        commands.put(COMMAND_OPEN_BILL, new OpenBill());
        commands.put(COMMAND_CANCEL_BILL, new CancelBill());
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
