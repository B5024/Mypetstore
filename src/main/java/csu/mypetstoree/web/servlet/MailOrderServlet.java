package csu.mypetstoree.web.servlet;

import csu.mypetstoree.domain.*;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MailOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Order order = (Order)session.getAttribute("order1");
        Account account = (Account)session.getAttribute("loginAccount");
        Cart cart = (Cart)session.getAttribute("cart");
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        String chooseLastTotal = session.getAttribute("ChooseLastTotal").toString();
        String email = account.getEmail();

        String emailString = "<div id=\"Catalog\">\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <th colspan=5>Order</th>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th colspan=5>Payment Details</th>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Card Type:</td>\n" +
                "                <td colspan=4>\n" +
                order.getCardType() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Card Number:</td>\n" +
                "                <td colspan=4>\n" +
                order.getCreditCard() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Expiry Date (MM/YYYY):</td>\n" +
                "                <td colspan=4>" +
                order.getExpiryDate() +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th colspan=5>Billing Address</th>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>First Name:</td>\n" +
                "                <td colspan=4>\n" +
                order.getBillToFirstName() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Last Name:</td>\n" +
                "                <td colspan=4>\n" +
                order.getBillToLastName() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Address1:</td>\n" +
                "                <td colspan=4>\n" +
                order.getBillAddress1() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Address2:</td>\n" +
                "                <td colspan=4>\n" +
                order.getBillAddress2() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>City:</td>\n" +
                "                <td colspan=4>\n" +
                order.getBillCity() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>State:</td>\n" +
                "                <td colspan=4>\n" +
                order.getBillState() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Zip:</td>\n" +
                "                <td colspan=4>\n" +
                order.getBillZip() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Country:</td>\n" +
                "                <td colspan=4>\n" +
                order.getBillCountry() +
                "                </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "                <td colspan=5>\n" +
                "                    Shipping Address\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>First Name:</td>\n" +
                "                <td colspan=4>\n" +
                order.getShipToFirstName() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Last Name:</td>\n" +
                "                <td colspan=4>\n" +
                order.getShipToLastName() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Address1:</td>\n" +
                "                <td colspan=4>\n" +
                order.getShipAddress1() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Address2:</td>\n" +
                "                <td colspan=4>\n" +
                order.getShipAddress2() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>City:</td>\n" +
                "                <td colspan=4>\n" +
                order.getShipCity() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>State:</td>\n" +
                "                <td colspan=4>\n" +
                order.getShipState() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Zip:</td>\n" +
                "                <td colspan=4>\n" +
                order.getShipZip() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Country:</td>\n" +
                "                <td colspan=4>\n" +
                order.getShipCountry() +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Courier:</td>\n" +
                "                <td colspan=4>UPS</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>Status</td>\n" +
                "                <td colspan=4>P</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>Item ID</th>\n" +
                "                <th>Description</th>\n" +
                "                <th>Quantity</th>\n" +
                "                <th>Price</th>\n" +
                "                <th>Total Cost</th>\n" +
                "            </tr>\n";

        if(cart.getNumberOfItems() == 0)
            emailString += "<tr>\n" +
                    "                    <td colspan=\"5\"><b>Your cartItems is empty.</b></td>\n" +
                    "                </tr>";

        for(CartItem cartItem : cartItems){
            Item item = cartItem.getItem();
            emailString += "<tr>\n" +
                    "                    <td>" +
                    item.getItemId() +
                    "</td>\n" +
                    "                    <td>" +
                    item.getAttribute1() + " " +
                    item.getProduct().getName() +
                    "</td>\n" +
                    "                    <td>" +
                    cartItem.getQuantity() +
                    "</td>\n" +
                    "                    <td>" +
                    item.getListPrice() +
                    "</td>\n" +
                    "                    <td>" +
                    cartItem.getTotal() +
                    "</td>\n" +
                    "                </tr>";
        }

        emailString += "<tr>\n" +
                "                <td colspan=5>Total:$" +
                chooseLastTotal +
                "</td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "</div>";

        //创建Properties类用于记录邮箱的一些属性
        Properties props = new Properties();
        //表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号
        props.put("mail.smtp.port","587");
        props.put("mail.smtp.starttls.enable", "true");
        //此处填写，写信人的账号
        props.put( "mail.user","2598882405@qq.com");
        //此处填写16位STMP口令
        props.put( "mail.password" ,"ljkjetivfrwkdjif");

        //构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                //用户名、密码
                String userName = props.getProperty( "mail.user" );
                String password = props.getProperty( "mail.password");
                return new PasswordAuthentication(userName,password);
            }
        };
        //使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        //创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        try {
            //设置发件人
            InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
            message.setFrom(form);
            //设置收件人的邮箱
            InternetAddress to = new InternetAddress(email);
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            //设置邮件标题
            message.setSubject("My Pet Store Order");
            //设置邮件内容体
            message.setContent("感谢您在JPetStore的购买！您的订单信息如下：\n"+emailString,"text/html;charset = UTF-8");
            //发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/WEB-INF/jsp/order/confirmedOrder.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
