package csu.mypetstoree.web.servlet;

import java.io.IOException;
import java.util.List;

import csu.mypetstoree.domain.Cart;
import csu.mypetstoree.domain.CartItem;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartFormServlet extends HttpServlet {

    private CatalogService catalogService;
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    public CartFormServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //loading。。。。。
        catalogService = new CatalogService();

        HttpSession session = req.getSession();
        List<CartItem> cartItems = catalogService.getCartItemList();
        Cart cart = new Cart();
        for (CartItem cartItem : cartItems) {
            cart.addItem(cartItem.getItem(),cartItem.isInStock());
        }
        //这里可以改进 只需要quantity就可以了
        cart.setItemList(cartItems);

        session.setAttribute("cart", cart);
        req.getRequestDispatcher("/WEB-INF/jsp/cart/cart.jsp").forward(req, resp);
    }
}
