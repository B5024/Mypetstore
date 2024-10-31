package mypetstore.web.servlet;

import mypetstore.domain.Cart;
import mypetstore.domain.Item;
import mypetstore.service.CatalogService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String workingItemId = req.getParameter("workingItemId");   //获取请求中workingItemId的字符串
        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null)
            cart = new Cart();           //若session内未得到cart对象则new Cart()

        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);  //如果cart中包含workingItemId的商品，则对应商品增加数量
        }
        else {
            CatalogService catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }

        session.setAttribute("cart", cart);
        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
