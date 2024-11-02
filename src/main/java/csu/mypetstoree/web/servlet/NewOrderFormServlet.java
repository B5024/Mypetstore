package csu.mypetstoree.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet {
    private static final String FORM = "/WEB-INF/jsp/order/newOrder.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("account");
        response.sendRedirect("signon");
//        if (account == null) {
//            response.sendRedirect("signon");
//        }
//        else {
//            request.getRequestDispatcher(FORM).forward(request,response);
//        }
    }
}
