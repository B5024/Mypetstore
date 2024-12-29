package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Product;
import csu.mypetstoree.service.CatalogService;
import csu.mypetstoree.service.LogsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import csu.mypetstoree.domain.Account;
import csu.mypetstoree.service.AccountService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SignOnServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    private Account account;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("loginAccount", account);

        if(account.isListOption()){
            CatalogService catalogService = new CatalogService();
            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            session.setAttribute("myList", myList);
        }

        String username = account.getUsername();
        resp.sendRedirect("mainForm");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        account = AccountService.getAccount(username, password);
        String Msg;
        if (account == null) {
            Msg = "username or password wrong";
        }else{
            Msg = "logged in";
        }
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.print(Msg);
    }
}
