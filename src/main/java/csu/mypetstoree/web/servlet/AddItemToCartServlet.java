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
import java.util.List;

import csu.mypetstoree.domain.Cart;
import csu.mypetstoree.domain.Item;
import csu.mypetstoree.service.CatalogService;
import csu.mypetstoree.service.LogsService;

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
            String workingItemId = req.getParameter("workingItemId");
            String username = account.getUsername();

            catalogService = new CatalogService();
            List<CartItem> cartItems = catalogService.getCartItemList(username);
            Cart cart = new Cart();
            cart.setCartItemList(cartItems);
            cart.setItemMap(cartItems);

            //如果存在这个商品的话，就增加数量，并把这个商品添加到
            if (cart.containsItemId(workingItemId)) {
                CartItem cartItem = new CartItem();
                //添加数量
                cart.incrementQuantityByItemId(workingItemId);
                catalogService.addCartItem(cart.getCartItemById(workingItemId),username);
                LogsService.insertCartLogs(username,"add",workingItemId);
            } else {
                //这里把赋值cart提供给前端
                boolean isInStock = catalogService.isItemInStock(workingItemId);
                Item item = catalogService.getItem(workingItemId);
                cart.addItem(item, isInStock);

                //这里存入数据库
                CatalogService catalogService = new CatalogService();
                CartItem cartItem = new CartItem();
                cartItem.setItem(item);
                cartItem.setQuantity(1);
                cartItem.setInStock(isInStock);
                catalogService.addCartItem(cartItem,username);
                LogsService.insertCartLogs(username,"add",workingItemId);
            }

            session.setAttribute("cart", cart);
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }
    }
}
