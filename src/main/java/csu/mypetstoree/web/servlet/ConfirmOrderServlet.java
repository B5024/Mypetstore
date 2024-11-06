package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.domain.Cart;
import csu.mypetstoree.domain.CartItem;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.xml.catalog.Catalog;
import java.io.IOException;
import java.util.List;

public class ConfirmOrderServlet extends HttpServlet {
    private static final String FORM = "/WEB-INF/jsp/order/newOrder.jsp";
    private CatalogService catalogService = new CatalogService();
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();

        Account account = (Account) session.getAttribute("loginAccount");
        String username = account.getUsername();
        catalogService = new CatalogService();
        List<CartItem> cartItems = catalogService.getCartItemList(username);

        for (CartItem cartItem : cartItems) {
            catalogService.removeItemById(cartItem.getItem().getItemId(),username);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/order/confirmedOrder.jsp").forward(req, resp);
    }
}
