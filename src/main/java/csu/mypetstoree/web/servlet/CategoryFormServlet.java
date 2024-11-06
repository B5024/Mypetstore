package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.domain.Category;
import csu.mypetstoree.domain.Product;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class CategoryFormServlet extends HttpServlet {

    private CatalogService catalogService;
    private static final String CATEGORY = "/WEB-INF/jsp/catalog/category.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        catalogService = new CatalogService();
        Category category = catalogService.getCategory(categoryId);
        List <Product> productList = catalogService.getProductListByCategory(categoryId);

        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        session.setAttribute("account",account);
        session.setAttribute("category", category);
        session.setAttribute("productList", productList);
        //找到Id之后应该要查出对应的参数然是送进去category.jsp中
//        System.out.println(categoryId);
//        System.out.println(productList);
        req.getRequestDispatcher(CATEGORY).forward(req, resp);
    }
}
