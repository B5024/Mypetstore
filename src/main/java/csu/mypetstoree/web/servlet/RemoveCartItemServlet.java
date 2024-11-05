package csu.mypetstoree.web.servlet;

import java.io.IOException;

import csu.mypetstoree.domain.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import csu.mypetstoree.domain.Cart;
import csu.mypetstoree.domain.Item;
import csu.mypetstoree.service.LogsService;

public class RemoveCartItemServlet extends HttpServlet {
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    public RemoveCartItemServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        String username = account.getUsername();
        String workingItemId = req.getParameter("workingItemId");
        Cart cart = (Cart)session.getAttribute("cart");
        Item item = cart.removeItemById(workingItemId);

        if (item == null) {
            session.setAttribute("errorMsg", "Attempted to remove null CartItem from Cart.");
            req.getRequestDispatcher("/WEB-INF/jsp/common/error.jsp").forward(req, resp);
        } else {
            LogsService.insertCartLogs(username,"remove",workingItemId);
            req.getRequestDispatcher("/WEB-INF/jsp/cart/cart.jsp").forward(req, resp);
        }

    }
}
