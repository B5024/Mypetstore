package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Product;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ProductDescServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        CatalogService catalogService = new CatalogService();
        List <Product> productList = catalogService.getProductListByCategory(categoryId);

        String POPUP = "<table>\n" +
                "        <tr>\n" +
                "            <th>Product ID</th>\n" +
                "            <th>Name</th>\n" +
                "        </tr>\n";

        for (Product product : productList) {
            POPUP = POPUP  + "<tr>\n" +
                    "                <td>" + product.getProductId() + "</td>\n" +
                    "                <td>" + product.getName() + "</td>\n" +
                    "            </tr>\n";
        }

        POPUP = POPUP + "</table>";

        resp.setContentType("text/html");
        resp.getWriter().print(POPUP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
