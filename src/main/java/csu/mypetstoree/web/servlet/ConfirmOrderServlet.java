package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.domain.Cart;
import csu.mypetstoree.domain.CartItem;
import csu.mypetstoree.domain.Order;
import csu.mypetstoree.service.CatalogService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.xml.catalog.Catalog;
import java.io.IOException;
import java.util.List;

public class ConfirmOrderServlet extends HttpServlet {

    private String cardType;
    private String creditCard;
    private String expiryDate;
    private String billToFirstName;
    private String billToLastName;
    private String billAddress1;
    private String billAddress2;
    private String billCity;
    private String billState;
    private String billZip;
    private String billCountry;
    private String shipToFirstName;
    private String shipToLastName;
    private boolean shippingAddressRequired;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;
    private static final String FORM = "/WEB-INF/jsp/order/newOrder.jsp";
    private CatalogService catalogService = new CatalogService();
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");

        this.cardType = req.getParameter("cardType");
        this.creditCard = req.getParameter("creditCard");
        this.expiryDate = req.getParameter("expiryDate");
        this.billToFirstName = req.getParameter("billToFirstName");
        this.billToLastName = req.getParameter("billToLastName");
        this.billAddress1 = req.getParameter("billAddress1");
        this.billAddress2 = req.getParameter("billAddress2");
        this.billCity = req.getParameter("billCity");
        this.billState = req.getParameter("billState");
        this.billZip = req.getParameter("billZip");
        this.billCountry = req.getParameter("billCountry");
        this.shipToFirstName = account.getFirstName();
        this.shipToLastName = account.getLastName();
        this.shipAddress1 = account.getAddress1();
        this.shipAddress2 =account.getAddress2();
        this.shipCity = account.getCity();
        this.shipState = account.getState();
        this.shipZip = account.getZip();
        this.shipCountry = account.getCountry();
        this.shippingAddressRequired = Boolean.parseBoolean(req.getParameter("shippingAddressRequired"));

        Order order1 = new Order(shipAddress1,shipAddress2,shipCity,
                shipState,shipZip,shipCountry,billAddress1,billAddress2,
                billCity,billState,billZip,billCountry,billToFirstName,
                billToLastName,shipToFirstName,shipToLastName,creditCard,expiryDate,cardType);
        session.setAttribute("order1", order1);


        String username = account.getUsername();
        catalogService = new CatalogService();
        List<CartItem> cartItems = catalogService.getCartItemList(username);

        for (CartItem cartItem : cartItems) {
            catalogService.removeItemById(cartItem.getItem().getItemId(),username);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/order/confirmedOrder.jsp").forward(req, resp);
    }
}
