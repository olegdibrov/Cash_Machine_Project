package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.Product;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.BillService;
import com.training.service.impl.ProductService;
import com.training.util.ResourceManager;
import com.training.util.constants.PageKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ViewBillPage implements Command {
    private ProductService productService = (ProductService) ServiceFactory.getService(ServiceKey.PRODUCT_SERVICE);
    private BillService billService = (BillService) ServiceFactory.getService(ServiceKey.BILL_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        HttpSession session = req.getSession();
        Integer idBill = (Integer) session.getAttribute("id_bill");
        List<Product> products = productService.getAllProducts();

        if (user != null || (user.getRole() == 1)) {
            if (idBill == null) {
                String action = req.getParameter("bill");

                idBill = billService.createNewBill(LocalDate.now(), user.getId(), 1);
                if (idBill != null) {
                    session.setAttribute("products", products);
                    session.setAttribute("id_bill", idBill);
                    req.getRequestDispatcher(PageKey.BILL_PAGE.toString()).forward(req, resp);
                } else {
                    req.setAttribute("msg", ResourceManager.INSTANCE.getValue("error"));
                    req.getRequestDispatcher(PageKey.ERROR_PAGE.toString()).forward(req, resp);
                }

            } else {
                session.setAttribute("products", products);
                req.getRequestDispatcher(PageKey.BILL_PAGE.toString()).forward(req, resp);

            }
        } else {
            req.getRequestDispatcher(PageKey.HOME_PAGE.toString()).forward(req, resp);

        }
    }
}
