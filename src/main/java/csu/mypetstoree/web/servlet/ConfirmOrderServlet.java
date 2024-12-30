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
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderServlet extends HttpServlet {
    private CatalogService catalogService = new CatalogService();
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //分配内存
        HttpSession session = req.getSession();
        catalogService = new CatalogService();

        //获得account
        Account account = (Account) session.getAttribute("loginAccount");
        String username = account.getUsername();

        //从session中获取提交的订单数据
        String commitCartData = session.getAttribute("commitCartData").toString();

        // 将字符串转换为JSONArray对象
        JSONArray jsonArray = new JSONArray(commitCartData);
        //直接处理jsonArray.getString(i)使用getCartItemById的方法
        List<CartItem> cartItems = new ArrayList<CartItem>();
        for (int i = 0; i < jsonArray.length(); i++) {
            CartItem cartItem = catalogService.GetCartItemById(username,jsonArray.getString(i));
            //我的GetCart方法没有对Item进行初始化 暂时在这里进行初始化
            cartItem.setItem(catalogService.getItem(jsonArray.getString(i)));
            cartItems.add(cartItem);
        }
        session.setAttribute("cartItems", cartItems);

        //重新计算总价
        Cart cart = new Cart(cartItems);
        session.setAttribute("ChooseLastTotal", cart.getSubTotal());

        //删除特定的商品
        for (CartItem cartItem1 : cartItems) {
            catalogService.removeItemById(cartItem1.getItem().getItemId(),username);
        }
        req.getRequestDispatcher("/WEB-INF/jsp/order/confirmedOrder.jsp").forward(req, resp);
    }
}
