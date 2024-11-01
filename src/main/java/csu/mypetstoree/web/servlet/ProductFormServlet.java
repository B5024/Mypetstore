package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Item;
import csu.mypetstoree.service.CatelogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductFormServlet extends HttpServlet {
    private CatelogService catelogService;
    private static final String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        catelogService = new CatelogService();

        List<Item> itemList = catelogService.getItemListByProduct(productId);

        HttpSession session = req.getSession();
        session.setAttribute("itemList", itemList);


        req.getRequestDispatcher(PRODUCT_FORM).forward(req, resp);
    }
}
