package csu.mypetstoree.web.servlet;

import csu.mypetstoree.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class isMailExistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        if( AccountService.isMailExist(email) ){
            out.print("exist");
        }else{
            out.print("not exist");
        }
    }
}
