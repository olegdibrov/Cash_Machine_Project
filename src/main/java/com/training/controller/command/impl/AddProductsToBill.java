package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.PaymentService;
import com.training.service.impl.ProductService;
import com.training.util.CommandManager;
import com.training.util.ResourceManager;
import com.training.util.constants.CommandKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
public class AddProductsToBill implements Command {
    private ProductService productService = (ProductService) ServiceFactory.getService(ServiceKey.PRODUCT_SERVICE);
    private PaymentService paymentService = (PaymentService) ServiceFactory.getService(ServiceKey.PAYMENT_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            Integer idBill = (Integer) session.getAttribute("id_bill");
            if (user == null) {
                resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_LOGIN_PAGE.toString()));
                return;
            }
            Integer idProduct = Integer.parseInt(req.getParameter("id_product"));
            Integer quantity = Integer.parseInt(req.getParameter("quantity"));

            if (!productService.exist(idProduct, quantity)) {
                session.setAttribute("orderStatus", ResourceManager.INSTANCE.getValue("bill.add.unavailableAdd"));
                resp.sendRedirect(req.getHeader("Referer"));
                return;
            } else {
                if (paymentService.addProductToBill(idBill, idProduct, quantity)) {
                    session.setAttribute("orderStatus", ResourceManager.INSTANCE.getValue("bill.add.unavailableAdd"));
                    resp.sendRedirect(req.getHeader("Referer"));
                } else {
                    session.setAttribute("orderStatus", ResourceManager.INSTANCE.getValue("bill.add.successAdded"));
                    resp.sendRedirect(req.getHeader("Referer"));
                }

            }
        } else {
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

}
