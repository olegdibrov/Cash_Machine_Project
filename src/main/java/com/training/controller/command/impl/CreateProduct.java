package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.ProductService;
import com.training.util.ResourceManager;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateProduct implements Command {

    private ProductService productService = (ProductService) ServiceFactory.getService(ServiceKey.PRODUCT_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute("user");
        if (req.getMethod().equalsIgnoreCase("POST") && user.getRole() == 4) {
            createProduct(req, resp);
        } else {

            session.setAttribute("product_status", ResourceManager.INSTANCE.getValue("product.create.unavailable"));
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            Double cost = Double.parseDouble(req.getParameter("cost"));
            Integer quantity = Integer.parseInt(req.getParameter("quantity"));
            boolean isValid = productService.validateString(name) &&
                    productService.validateString(description) &&
                    cost != null && quantity != null;
            if (isValid) {
                productService.createProduct(name, description, cost, quantity);
                req.getSession().setAttribute("product_status", ResourceManager.INSTANCE.getValue("product.create.success"));
            } else {
                req.getSession().setAttribute("product_status", ResourceManager.INSTANCE.getValue("product.create.unavailable"));
            }
        } catch (ClassCastException | NullPointerException ex) {
            req.getSession().setAttribute("product_status", ResourceManager.INSTANCE.getValue("product.create.unavailable"));
        }
    }
}
