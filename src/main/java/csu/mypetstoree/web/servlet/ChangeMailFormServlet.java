package csu.mypetstoree.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangeMailFormServlet extends HttpServlet {

    private static final String CHANGE_MAIL_FORM = "/WEB-INF/jsp/account/changeMail.jsp";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("mailFrom", "changeMail");
        req.getRequestDispatcher(CHANGE_MAIL_FORM).forward(req, resp);
    }
}
