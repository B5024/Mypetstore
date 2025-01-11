package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.service.AccountService;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    private static final String REGISTER_FORM = "/WEB-INF/jsp/account/register.jsp";

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String emailcode;
    private String phone;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String Msg;

    private HttpSession session;
    private CatalogService catalogService;


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession();

        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        this.confirmPassword = req.getParameter("confirmPassword");
        this.firstname = req.getParameter("firstname");
        this.lastname = req.getParameter("lastname");
        this.email = session.getAttribute("email").toString();
        this.emailcode = req.getParameter("emailcode");
        this.addr1 = req.getParameter("addr1");
        this.addr2 = req.getParameter("addr2");
        this.city = req.getParameter("city");
        this.state = req.getParameter("state");
        this.zip = req.getParameter("zip");
        this.country = req.getParameter("country");
        this.phone = req.getParameter("phone");

        catalogService = new CatalogService();
        catalogService.NewCartItemTable(username);

        Account account = new Account(username,password,email,firstname,lastname,addr1,addr2,city,state
                ,zip,country,phone);
        AccountService.addAccount(account);
        resp.sendRedirect("signOnForm");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailcode = req.getParameter("emailcode");
        String username = req.getParameter("username");
        HttpSession session = req.getSession();
        String emailcode2 = session.getAttribute("emailcode").toString();

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        Account account = AccountService.getAccountByUsername(username);
        if (account != null) {
            out.print("exist");
        }else {
            out.print("not exist");
        }

        if (emailcode.equals(emailcode2)) {
            out.print(":match");
        }else {
            out.print(":not match");
        }
    }
}
