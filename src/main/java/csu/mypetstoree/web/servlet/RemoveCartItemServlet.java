package csu.mypetstoree.web.servlet;

import java.io.IOException;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import csu.mypetstoree.domain.Cart;
import csu.mypetstoree.domain.Item;
import csu.mypetstoree.service.LogsService;

public class RemoveCartItemServlet extends HttpServlet {
    public CatalogService catalogService;
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    public RemoveCartItemServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        String username = account.getUsername();
        String workingItemId = req.getParameter("workingItemId");

        //获取数据库中的cart
        catalogService = new CatalogService();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
        }
        cart.setCartItemList(catalogService.getCartItemList(username));


        //删除在数据库中的item
        catalogService.removeItemById(workingItemId,username);

        //在前端实现对应的删除，然后表现出来
        //读取remove后的cartItems
        cart.setCartItemList(catalogService.getCartItemList(username));
        session.setAttribute("cart", cart);

//        这里是使用session方法来和删除Items
//        Item = cart.removeItemById(workingItemId);
//        System.out.println(item.getItemId());
//        if (item == null) {
//            session.setAttribute("errorMsg", "Attempted to remove null CartItem from Cart.");
//            req.getRequestDispatcher("/WEB-INF/jsp/common/error.jsp").forward(req, resp);
//        } else {
////            LogsService.insertCartLogs(username,"remove",workingItemId);
//            System.out.println();
//            req.getRequestDispatcher("/WEB-INF/jsp/cart/cart.jsp").forward(req, resp);
//        }
//
        req.getRequestDispatcher("/WEB-INF/jsp/cart/cart.jsp").forward(req, resp);

    }
}
