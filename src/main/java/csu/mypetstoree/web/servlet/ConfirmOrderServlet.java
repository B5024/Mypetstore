package csu.mypetstoree.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ConfirmOrderServlet extends HttpServlet {
    private static final String FORM = "/WEB-INF/jsp/order/newOrder.jsp";
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        req.getRequestDispatcher("/WEB-INF/jsp/order/confirmedOrder.jsp").forward(req, resp);
    }
}
