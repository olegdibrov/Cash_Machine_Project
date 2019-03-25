package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.PaymentPreview;
import com.training.model.entity.Product;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.PaymentService;
import com.training.service.impl.ProductService;
import com.training.util.constants.PageKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewProductsInBill implements Command {
    private PaymentService paymentService = (PaymentService) ServiceFactory.getService(ServiceKey.PAYMENT_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer idBill = (Integer) req.getSession().getAttribute("id_bill");

        List<PaymentPreview> products = paymentService.findProductsByIdBill(idBill);
        req.setAttribute("products", products);

        req.getRequestDispatcher(PageKey.PRODUCTS_PAGE.toString()).forward(req, resp);
    }
}
