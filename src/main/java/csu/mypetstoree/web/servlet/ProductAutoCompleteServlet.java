package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Product;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.alibaba.fastjson.JSON;


public class ProductAutoCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);

        String result = JSON.toJSONString(productList);
        System.out.println(result);

        resp.setContentType("text/json");
        PrintWriter out = resp.getWriter();
        out.println(result);
    }
}
