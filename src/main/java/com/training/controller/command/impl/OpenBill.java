package com.training.controller.command.impl;

import com.training.controller.command.Command;
import com.training.model.entity.Bill;
import com.training.model.entity.User;
import com.training.service.ServiceFactory;
import com.training.service.impl.BillService;
import com.training.util.CommandManager;
import com.training.util.Pagination;
import com.training.util.constants.CommandKey;
import com.training.util.constants.PageKey;
import com.training.util.constants.ServiceKey;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenBill implements Command {
    private BillService billService = (BillService) ServiceFactory.getService(ServiceKey.BILL_SERVICE);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Bill> bills = new ArrayList<>();
        Pagination paginator;
        // = Pagination.getPaginator(showTimeService.getAll(), 10);
        if (user != null) {
            if (req.getParameter("id_bill") == null) {
                if (user.getRole() == 2) {
                    paginator = Pagination.getPaginator(billService.getBillsByUser(user.getId()), 10);
                    req.setAttribute("noOfPages", paginator.getNumberOfPages());
                    req.setAttribute("currentPage", paginator.getCurrentPage(req.getParameter("page")));
                    req.setAttribute("bills", paginator.paginate());
                    // bills = billService.getBillsByUser(user.getId());
                } else if (user.getRole() == 3) {
                    paginator = Pagination.getPaginator(billService.getAllBills(), 10);
                    req.setAttribute("noOfPages", paginator.getNumberOfPages());
                    req.setAttribute("currentPage", paginator.getCurrentPage(req.getParameter("page")));
                    req.setAttribute("bills", paginator.paginate());
                    // bills = billService.getAllBills();
                }
                req.getRequestDispatcher(PageKey.CHOOSE_BILL.toString()).forward(req, resp);
            } else {
                Integer idBill = Integer.parseInt( req.getParameter("id_bill"));
                session.setAttribute("id_bill", idBill);
                resp.sendRedirect(CommandManager.getRedirect(CommandKey.COMMAND_VIEW_PRODUCTS_IN_BILL.toString()));

            }
        }
//        req.setAttribute("noOfPages", paginator.getNumberOfPages());
//        req.setAttribute("currentPage", paginator.getCurrentPage(req.getParameter("page")));
//        req.setAttribute("showTimes", paginator.paginate());
        //session.setAttribute("bills", bills);
  //      req.getRequestDispatcher(PageKey.CHOOSE_BILL.toString()).forward(req, resp);

    }

}
