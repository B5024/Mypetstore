package csu.mypetstoree.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import csu.mypetstoree.domain.Account;
import csu.mypetstoree.service.AccountService;

import java.io.IOException;

public class SignOnServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    private String username;
    private String password;
    private String Msg;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SignOnServlet doPost");
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        if (!validate()){
            req.setAttribute("signOnMsg", Msg );
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
        }
        else {
            Account account = AccountService.getAccount(username, password);
            if (account != null) {
                Msg = "username or password wrong";
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            }else {
                HttpSession session = req.getSession();
                session.setAttribute("loginAccount", account);
                resp.sendRedirect("main");
            }
        }
    }

    //校验
    private boolean validate(){
        if(username == null || username.isEmpty()){
            Msg = "Username is required";
            return false;
        }
        if(password == null || password.isEmpty()){
            Msg = "Password is required";
            return false;
        }
        if(!containsBothLettersAndNumbers(password)){
            Msg = "Password should have at least one letter and one number";
            return false;
        }
        return true;
    }


    private boolean containsBothLettersAndNumbers(String s) {
        boolean hasLetter = false;
        boolean hasDigit = false;

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }

            if (hasLetter && hasDigit) {
                return true;
            }
        }
        return false;
    }

}
