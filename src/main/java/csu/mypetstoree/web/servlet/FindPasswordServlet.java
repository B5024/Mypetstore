package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class FindPasswordServlet extends HttpServlet {

    private String username;
    private String password;
    private String confirmPassword;
    private String emailcode;

    private String Msg;

    private HttpSession session;

    private static final String FIND_PASSWORD_FORM = "/WEB-INF/jsp/account/findpassword.jsp";

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession();

        this.password=req.getParameter("password");
        this.confirmPassword=req.getParameter("confirmPassword");
        this.emailcode=req.getParameter("emailcode");
        this.username=req.getParameter("username");

        if (!validateRegistration()){
            req.setAttribute("findpasswordMsg", Msg );
            req.getRequestDispatcher(FIND_PASSWORD_FORM).forward(req, resp);
        }else {
            Account account = new Account(username,password);
            AccountService.updateSignOn(account);
            resp.sendRedirect("signOnForm");
        }
    }

    private boolean validateRegistration(){
        if(password == null || password.isEmpty()){
            Msg = "Password is required";
            return false;
        }else if(!containsBothLettersAndNumbers(password)){
            Msg = "Password should have at least one letter and one number";
            return false;
        }
        else if(!password.equals(confirmPassword)){
            Msg = "Confirm Password do not match";
            return false;
        }else if(!emailcode.equals(session.getAttribute("emailcode"))){
            Msg = "Email code does not match";
            return false;
        }else if(username==null || username.isEmpty()){
            Msg = "Username is required";
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
