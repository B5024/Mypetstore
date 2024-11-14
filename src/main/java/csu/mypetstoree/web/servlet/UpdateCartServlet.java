package csu.mypetstoree.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import csu.mypetstoree.domain.Cart;
import csu.mypetstoree.domain.CartItem;

public class UpdateCartServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private CatalogService catalogService;
    public UpdateCartServlet() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        //
//
        Account account = (Account) session.getAttribute("loginAccount");
        String username = account.getUsername();
        catalogService = new CatalogService();
        List<CartItem> cartItemList = catalogService.getCartItemList(username);

        Cart cart = (Cart)session.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getAllCartItems();

        while(cartItems.hasNext()) {
            CartItem cartItem = (CartItem)cartItems.next();
            String itemId = cartItem.getItem().getItemId();

            try {
                String quantityString = req.getParameter(itemId);
                int quantity = Integer.parseInt(quantityString);
                cart.setQuantityByItemId(itemId, quantity);
                //写入对应的方法当中
                catalogService.UpdateCartItem(username,itemId,quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception var10) {
            }
        }

        session.setAttribute("cart", cart);

//        for (CartItem cartItem : cartItemList) {
//            String itemId = cartItem.getItem().getItemId();
//        }
        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
