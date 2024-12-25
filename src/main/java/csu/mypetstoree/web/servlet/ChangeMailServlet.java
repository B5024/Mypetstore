package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.service.AccountService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class ChangeMailServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        account.setEmail(email);
        AccountService.updateAccount(account);
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.print("redirect");
    }
}
