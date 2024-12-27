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
import java.util.List;

public class SignOnServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    private String username;
    private String password;
    private String Msg;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("SignOnServlet doPost");
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        if (!validate()){
            req.setAttribute("signOnMsg", Msg );
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
        }
        else {
            Account account = AccountService.getAccount(username, password);
            if (account == null) {
                Msg = "username or password wrong";
                req.setAttribute("signOnMsg", Msg );
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            }else {
                HttpSession session = req.getSession();
                session.setAttribute("loginAccount", account);

                if(account.isListOption()){
                    CatalogService catalogService = new CatalogService();
                    List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
                    session.setAttribute("myList", myList);
                }

                LogsService.insertLoginLogs(username);
                resp.sendRedirect("mainForm");
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
