package csu.mypetstoree.web.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CartFormServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    public CartFormServlet() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/cart/cart.jsp").forward(req, resp);
    }
}
