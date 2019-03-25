package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.Product;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.ProductService;
import com.training.util.ResourceManager;
import com.training.util.constants.PageKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UpdateProductInfo implements Command {
    private ProductService productService = (ProductService) ServiceFactory.getService(ServiceKey.PRODUCT_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute("user");
        if (req.getMethod().equalsIgnoreCase("POST") && user.getRole() == 4) {
            updateProduct(req, resp);
            List<Product> products = productService.getAllProducts();
            session.setAttribute("products", products);
        } else {
            session.setAttribute("product_status", ResourceManager.INSTANCE.getValue("product.update.unavailable"));
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double cost = Double.parseDouble(req.getParameter("cost"));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));

        boolean isValid = productService.validateString(name) &&
                productService.validateString(description) &&
                cost != null && quantity != null && id != null;

        if (isValid) {
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setCost(cost);
            product.setQuantity(quantity);
            productService.updateProduct(product);
            req.getSession().setAttribute("product_status", ResourceManager.INSTANCE.getValue("product.update.success"));
        } else {
            req.getSession().setAttribute("product_status", ResourceManager.INSTANCE.getValue("product.update.unavailable"));
        }

    }
}
