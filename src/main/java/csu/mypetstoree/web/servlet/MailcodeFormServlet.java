package csu.mypetstoree.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class MailcodeFormServlet extends HttpServlet {

    private static final String FORM = "/WEB-INF/jsp/account/mailcode.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isFindPassword = request.getParameter("isFindPassword").equals("true");
        HttpSession session = request.getSession();
        if (isFindPassword) {
            session.setAttribute("isFindPassword", "true");
        }else {
            session.setAttribute("isFindPassword", "false");
        }
        request.getRequestDispatcher(FORM).forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}