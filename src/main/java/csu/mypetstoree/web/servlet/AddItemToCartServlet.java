package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.domain.CartItem;
import csu.mypetstoree.persistence.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import csu.mypetstoree.domain.Cart;
import csu.mypetstoree.domain.Item;
import csu.mypetstoree.service.CatalogService;

public class AddItemToCartServlet extends HttpServlet {
    private CatalogService catalogService;
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    public AddItemToCartServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        if (account == null) {
            resp.sendRedirect("signOnForm");
        }
        else {
            String username = account.getUsername();
            catalogService = new CatalogService();
            String workingItemId = req.getParameter("workingItemId");
            Cart cart = (Cart)session.getAttribute("cart");

            //read cart from dg
            if (cart == null) {
                cart = new Cart();
                cart.setCartItemList(catalogService.getCartItemList(username));
            }

            if (cart.containsItemId(workingItemId)) {
                //添加数量
                cart.setCartItemList(catalogService.getCartItemList(username));
                cart.incrementQuantityByItemId(workingItemId);
                for (CartItem cartItem : cart.getCartItemList()) {
                    catalogService.addCartItem(cartItem,username);
                }
            } else {
                CatalogService catalogService = new CatalogService();
                boolean isInStock = catalogService.isItemInStock(workingItemId);
                Item item = catalogService.getItem(workingItemId);
                cart.addItem(item, isInStock);

                CartItem cartItem = new CartItem();
                cartItem.setItem(item);
                //获取数量
                cartItem.setQuantity(item.getQuantity());
                cartItem.setInStock(isInStock);
                catalogService.addCartItem(cartItem,username);
            }



            session.setAttribute("cart", cart);
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }
    }
}
