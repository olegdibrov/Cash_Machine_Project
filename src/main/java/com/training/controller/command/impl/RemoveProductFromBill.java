package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.PaymentService;
import com.training.util.CommandManager;
import com.training.util.ResourceManager;
import com.training.util.constants.CommandKey;
import com.training.util.constants.PageKey;
import com.training.util.constants.ServiceKey;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveProductFromBill implements Command {

    private PaymentService paymentService = (PaymentService) ServiceFactory.getService(ServiceKey.PAYMENT_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute("user");
        if (req.getMethod().equalsIgnoreCase("POST") && user.getRole() == 3) {
            Integer idPayment = Integer.parseInt(req.getParameter("id_payment"));
            if (paymentService.removeProductFromBill(idPayment)) {
                session.setAttribute("paymentStatus", ResourceManager.INSTANCE.getValue("payments.remove.success"));
            }
            else {
                session.setAttribute("paymentStatus", ResourceManager.INSTANCE.getValue("payments.remove.unavailable"));
            }
        }
        resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_PRODUCTS_IN_BILL.toString()));
    }
}