package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.Product;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.ProductService;
import com.training.util.constants.PageKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewHomePage implements Command {
    private ProductService productService = (ProductService) ServiceFactory.getService(ServiceKey.PRODUCT_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Product> products = productService.getAllProducts();
        req.setAttribute("products", products);

        if (user == null || (user.getRole() != 1)){
            req.getRequestDispatcher(PageKey.HOME_PAGE.toString()).forward(req, resp);
        } else {
            req.getRequestDispatcher(PageKey.ADMIN_PAGE.toString()).forward(req, resp);
        }
    }
}
