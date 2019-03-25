package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.Product;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.ProductService;
import com.training.util.CommandManager;
import com.training.util.constants.CommandKey;
import com.training.util.constants.PageKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewStockPage implements Command {

    private ProductService productService = (ProductService) ServiceFactory.getService(ServiceKey.PRODUCT_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole() == 4) {
            List<Product> products = productService.getAllProducts();
            session.setAttribute("products", products);
            req.getRequestDispatcher(PageKey.STOCK_PAGE.toString()).forward(req, resp);
        } else {
            req.getRequestDispatcher(PageKey.HOME_PAGE.toString()).forward(req, resp);
        }
    }
}
