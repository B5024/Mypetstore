package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class EditAccountServlet extends HttpServlet {

    private static final String EDIT_ACCOUNT_FORM = "/WEB-INF/jsp/account/editAccount.jsp";

    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String confirmPassword;
    private String email;
    private String phone;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String Msg;
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption;
    private boolean bannerOption;


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");

        this.username = loginAccount.getUsername();
        this.password = req.getParameter("password");
        this.confirmPassword = req.getParameter("confirmPassword");
        this.firstname = req.getParameter("firstname");
        this.lastname = req.getParameter("lastname");
        this.email = req.getParameter("email");
        this.addr1 = req.getParameter("addr1");
        this.addr2 = req.getParameter("addr2");
        this.city = req.getParameter("city");
        this.state = req.getParameter("state");
        this.zip = req.getParameter("zip");
        this.country = req.getParameter("country");
        this.phone = req.getParameter("phone");
        this.favouriteCategoryId = req.getParameter("favouriteCategoryId");
        this.languagePreference = req.getParameter("languagePreference");
        this.listOption = Boolean.parseBoolean(req.getParameter("listOption"));
        this.bannerOption = Boolean.parseBoolean(req.getParameter("bannerOption"));

        if (!validateRegistration()){
            req.setAttribute("editAccountMsg", Msg );
            req.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(req, resp);
        }
        else {
            Account account = new Account(username,password,email,firstname,lastname,addr1,addr2,city,state
                    ,zip,country,phone,favouriteCategoryId,languagePreference,listOption,bannerOption);
            AccountService.updateAccount(account);
            resp.sendRedirect("mainForm");
        }
    }

    private boolean validateRegistration() {
        if (firstname == null || firstname.isEmpty()) {
            Msg = "First name is required";
            return false;
        }
        if (lastname == null || lastname.isEmpty()) {
            Msg = "Last name is required";
            return false;
        }
        if (email == null || email.isEmpty()) {
            Msg = "Email is required";
            return false;
        }
        if (!isValidEmail(email)) {
            Msg = "Invalid email format";
            return false;
        }
        if (password == null || password.isEmpty()) {
            Msg = "Password is required";
            return false;
        }
        if (confirmPassword == null || !confirmPassword.equals(password)) {
            Msg = "Confirm password does not match";
        }
        if (!containsBothLettersAndNumbers(password)) {
            Msg = "Password should have at least one letter and one number";
            return false;
        }
        if (addr1 == null || addr1.isEmpty()) {
            Msg = "Address Line 1 is required";
            return false;
        }
        if (city == null || city.isEmpty()) {
            Msg = "City is required";
            return false;
        }
        if (state == null || state.isEmpty()) {
            Msg = "State is required";
            return false;
        }
        if (zip == null || zip.isEmpty()) {
            Msg = "Zip Code is required";
            return false;
        }
        if (country == null || country.isEmpty()) {
            Msg = "Country is required";
            return false;
        }
        if (phone == null || phone.isEmpty()) {
            Msg = "Phone number is required";
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
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
