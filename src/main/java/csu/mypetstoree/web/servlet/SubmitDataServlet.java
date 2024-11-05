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


public class SubmitDataServlet extends HttpServlet {

    private CatalogService catalogService;
    private static final String PRODUCT = "/WEB-INF/jsp/catalog/product.jsp";
    private static final String NotFound = "/WEB-INF/jsp/catalog/nofound.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        catalogService = new CatalogService();
        Product product = new Product();
        product = catalogService.getProductByName(keyword);

        HttpSession session = req.getSession();
        if (product == null) {
            session.setAttribute("keyword", keyword);
            req.getRequestDispatcher(NotFound).forward(req, resp);
        }else {
            List<Item> itemList = catalogService.getItemListByProduct(product.getProductId());
            session.setAttribute("product", product);
            session.setAttribute("itemList", itemList);
            req.getRequestDispatcher(PRODUCT).forward(req, resp);
        }
    }
}
