package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.User;
import com.training.service.Service;
import com.training.service.ServiceFactory;
import com.training.service.impl.BillService;
import com.training.util.CommandManager;
import com.training.util.ResourceManager;
import com.training.util.constants.CommandKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelBill implements Command {

    private BillService billService = (BillService) ServiceFactory.getService(ServiceKey.BILL_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer idBill = (Integer) session.getAttribute("id_bill");

        if (user.getRole() == 3 && idBill != null) {
            if (billService.cancelBill(idBill)) {
                session.setAttribute("paymentStatus", ResourceManager.INSTANCE.getValue("payments.cancelbill.success"));
            } else {
                session.setAttribute("paymentStatus", ResourceManager.INSTANCE.getValue("payments.cancelbill.unavailable"));
            }
        } else
            session.setAttribute("paymentStatus", ResourceManager.INSTANCE.getValue("payments.cancelbill.unavailable"));
        resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_PRODUCTS_IN_BILL.toString()));
    }
}
