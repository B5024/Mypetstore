package mypetstore.web.servlet;

<<<<<<< HEAD
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
=======

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

>>>>>>> 0efe2ce20f90adf547974038b11101025579ce98
import java.io.IOException;

public class MainFormServlet extends HttpServlet {
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MAIN_FORM).forward(req, resp);
    }
}
