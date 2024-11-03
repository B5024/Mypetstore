package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet {
    private static final String FORM = "/WEB-INF/jsp/order/newOrder.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        if (account == null) {
            response.sendRedirect("signOnForm");
        }
        else {
            request.getRequestDispatcher(FORM).forward(request,response);
        }
    }
}
