package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Item;
import csu.mypetstoree.domain.Product;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class ProductFormServlet extends HttpServlet {
    private static final String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";
    private CatalogService catalogService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productId = req.getParameter("productId");


        catalogService = new CatalogService();
        Product product = catalogService.getProduct(productId);
        List<Item> itemList = catalogService.getItemListByProduct(productId);

//        System.out.println(productId);
//        System.out.println(product);
//        System.out.println(itemList);

        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);

        req.getRequestDispatcher(PRODUCT_FORM).forward(req, resp);
    }
}
