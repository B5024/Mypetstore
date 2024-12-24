package csu.mypetstoree.web.servlet;

import java.io.IOException;
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
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();

            try {
                String quantityString = req.getParameter(itemId);
                int quantity = Integer.parseInt(quantityString);
                cart.setQuantityByItemId(itemId, quantity);
                //写入对应的方法当中
                catalogService.UpdateCartItem(username,itemId,quantity);

                //这里已经更新数据了
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception var10) {
            }
        }

        System.out.println("完成更新");

        session.setAttribute("cart", cart);
//        这里的cart还是设置 但是不转发？ 然后使用
        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
