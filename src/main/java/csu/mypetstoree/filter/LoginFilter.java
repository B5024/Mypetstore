package csu.mypetstoree.filter;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.service.LogsService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/signOn")
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        chain.doFilter(request, response);
        if(req.getMethod().equals("POST")) {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("loginAccount");
            String username = account.getUsername();
            LogsService.insertLoginLogs(username);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
