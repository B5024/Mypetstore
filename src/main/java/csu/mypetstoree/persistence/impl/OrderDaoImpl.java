package csu.mypetstoree.persistence.impl;

import csu.mypetstoree.domain.Order;
import csu.mypetstoree.persistence.DBUtil;
import csu.mypetstoree.persistence.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    public List<Order> getOrdersByUsername(String username){
        List<Order> orders = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ORDERS_BY_USERNAME);
            statement.setString(1, "username");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                orders = new ArrayList<>();
                do {
                    Order order = new Order();

                    order.setBillAddress1(resultSet.getString("billAddress1"));
                    order.setBillAddress2(resultSet.getString("billAddress2"));
                    order.setBillCity(resultSet.getString("BILLCITY"));
                    order.setBillCountry(resultSet.getString("BILLCOUNTRY"));
                    order.setBillState(resultSet.getString("BILLSTATE"));
                    order.setBillToFirstName(resultSet.getString("BILLTOFIRSTNAME"));
                    order.setBillToLastName(resultSet.getString("BILLTOLASTNAME"));
                    order.setBillZip(resultSet.getString("BILLZIP"));

                    order.setShipAddress1(resultSet.getString("shipAddress1"));
                    order.setShipAddress2(resultSet.getString("shipAddress2"));
                    order.setShipCity(resultSet.getString("SHIPCITY"));
                    order.setShipCountry(resultSet.getString("SHIPCOUNTRY"));
                    order.setShipState(resultSet.getString("SHIPSTATE"));
                    order.setShipToFirstName(resultSet.getString("SHIPTOFIRSTNAME"));
                    order.setShipToLastName(resultSet.getString("SHIPTOLASTNAME"));
                    order.setShipZip(resultSet.getString("SHIPZIP"));

                    order.setCardType(resultSet.getString("CARDTYPE"));
                    order.setCourier(resultSet.getString("COURIER"));
                    order.setCreditCard(resultSet.getString("CREDITCARD"));
                    order.setExpiryDate(resultSet.getString("expiryDate"));
                    order.setLocale(resultSet.getString("LOCALE"));
                    order.setOrderDate(resultSet.getDate("ORDERDATE"));
                    order.setOrderId(resultSet.getInt("ORDERID"));
                    order.setTotalPrice(resultSet.getBigDecimal("TOTALPRICE"));
                    order.setUsername(resultSet.getString("username"));
                    order.setStatus(resultSet.getString("STATUS"));

                    orders.add(order);

                } while (resultSet.next());
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orders;
    };

    public Order getOrder(int orderId){
        Order order = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ORDER);
            statement.setString(1, "orderId");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                order = new Order();
                order.setBillAddress1(resultSet.getString("billaddr1"));
                order.setBillAddress2(resultSet.getString("billaddr2"));
                order.setBillCity(resultSet.getString("billcity"));
                order.setBillCountry(resultSet.getString("billcountry"));
                order.setBillState(resultSet.getString("billstate"));
                order.setBillToFirstName(resultSet.getString("billtofirstname"));
                order.setBillToLastName(resultSet.getString("billtolastname"));
                order.setBillZip(resultSet.getString("billzip"));
                order.setShipAddress1(resultSet.getString("shipaddr1"));
                order.setShipAddress2(resultSet.getString("shipaddr2"));
                order.setShipCity(resultSet.getString("shipcity"));
                order.setShipCountry(resultSet.getString("shipcountry"));
                order.setShipState(resultSet.getString("shipstate"));
                order.setShipToFirstName(resultSet.getString("shiptofirstname"));
                order.setShipToLastName(resultSet.getString("shiptolastname"));
                order.setShipZip(resultSet.getString("shipzip"));
                order.setCardType(resultSet.getString("cardtype"));
                order.setCourier(resultSet.getString("courier"));
                order.setCreditCard(resultSet.getString("creditcard"));
                order.setExpiryDate(resultSet.getString("expirydate"));
                order.setLocale(resultSet.getString("locale"));
                order.setOrderDate(resultSet.getDate("orderDate"));
                order.setOrderId(resultSet.getInt("orderId"));
                order.setTotalPrice(resultSet.getBigDecimal("totalprice"));
                order.setUsername(resultSet.getString("username"));
                order.setStatus(resultSet.getString("status"));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return order;
    };

    public void insertOrder(Order order){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER);
            statement.setInt(1, order.getOrderId());
            statement.setString(2, order.getUsername());
            statement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setString(4, order.getShipAddress1());
            statement.setString(5, order.getShipAddress2());
            statement.setString(6, order.getShipCity());
            statement.setString(7, order.getShipState());
            statement.setString(8, order.getShipZip());
            statement.setString(9, order.getShipCountry());
            statement.setString(10, order.getBillAddress1());
            statement.setString(11, order.getBillAddress2());
            statement.setString(12, order.getBillCity());
            statement.setString(13, order.getBillState());
            statement.setString(14, order.getBillZip());
            statement.setString(15, order.getBillCountry());
            statement.setString(16, order.getCourier());
            statement.setBigDecimal(17, order.getTotalPrice());
            statement.setString(18, order.getBillToFirstName());
            statement.setString(19, order.getBillToLastName());
            statement.setString(20, order.getShipToFirstName());
            statement.setString(21, order.getShipToLastName());
            statement.setString(22, order.getCreditCard());
            statement.setString(23, order.getExpiryDate());
            statement.setString(24, order.getCardType());
            statement.setString(25, order.getLocale());
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("Order Insert succeed");
            }else {
                System.out.println("Order Insert fail");
            }
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    public void insertOrderStatus(Order order){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_STATUS);
            statement.setInt(1, order.getOrderId()); // ORDERID
            statement.setInt(2, order.getLineItems().size()); // LINENUM
            statement.setTimestamp(3, new java.sql.Timestamp(order.getOrderDate().getTime())); // TIMESTAMP
            statement.setString(4, order.getStatus()); // STATUS
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("Order Status Insert succeed");
            }else {
                System.out.println("Order Status Insert fail");
            }
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    };


    private static final String GET_ORDER = "SELECT\n" +
            "      BILLADDR1 AS billAddress1,\n" +
            "      BILLADDR2 AS billAddress2,\n" +
            "      BILLCITY,\n" +
            "      BILLCOUNTRY,\n" +
            "      BILLSTATE,\n" +
            "      BILLTOFIRSTNAME,\n" +
            "      BILLTOLASTNAME,\n" +
            "      BILLZIP,\n" +
            "      SHIPADDR1 AS shipAddress1,\n" +
            "      SHIPADDR2 AS shipAddress2,\n" +
            "      SHIPCITY,\n" +
            "      SHIPCOUNTRY,\n" +
            "      SHIPSTATE,\n" +
            "      SHIPTOFIRSTNAME,\n" +
            "      SHIPTOLASTNAME,\n" +
            "      SHIPZIP,\n" +
            "      CARDTYPE,\n" +
            "      COURIER,\n" +
            "      CREDITCARD,\n" +
            "      EXPRDATE AS expiryDate,\n" +
            "      LOCALE,\n" +
            "      ORDERDATE,\n" +
            "      ORDERS.ORDERID,\n" +
            "      TOTALPRICE,\n" +
            "      USERID AS username,\n" +
            "      STATUS\n" +
            "    FROM ORDERS, ORDERSTATUS\n" +
            "    WHERE ORDERS.ORDERID = ?\n" +
            "      AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";

    private static final String GET_ORDERS_BY_USERNAME = "SELECT\n" +
            "      BILLADDR1 AS billAddress1,\n" +
            "      BILLADDR2 AS billAddress2,\n" +
            "      BILLCITY,\n" +
            "      BILLCOUNTRY,\n" +
            "      BILLSTATE,\n" +
            "      BILLTOFIRSTNAME,\n" +
            "      BILLTOLASTNAME,\n" +
            "      BILLZIP,\n" +
            "      SHIPADDR1 AS shipAddress1,\n" +
            "      SHIPADDR2 AS shipAddress2,\n" +
            "      SHIPCITY,\n" +
            "      SHIPCOUNTRY,\n" +
            "      SHIPSTATE,\n" +
            "      SHIPTOFIRSTNAME,\n" +
            "      SHIPTOLASTNAME,\n" +
            "      SHIPZIP,\n" +
            "      CARDTYPE,\n" +
            "      COURIER,\n" +
            "      CREDITCARD,\n" +
            "      EXPRDATE AS expiryDate,\n" +
            "      LOCALE,\n" +
            "      ORDERDATE,\n" +
            "      ORDERS.ORDERID,\n" +
            "      TOTALPRICE,\n" +
            "      USERID AS username,\n" +
            "      STATUS\n" +
            "    FROM ORDERS, ORDERSTATUS\n" +
            "    WHERE ORDERS.USERID = ?\n" +
            "      AND ORDERS.ORDERID = ORDERSTATUS.ORDERID\n" +
            "    ORDER BY ORDERDATE";

    private static final String INSERT_ORDER = "INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE,\n" +
            "      SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY,\n" +
            "      COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME,\n" +
            "      CREDITCARD, EXPRDATE, CARDTYPE, LOCALE)\n" +
            "    VALUES(?, ?, ?, ?, ?, ?,\n" +
            "      ?, ?, ?, ?, ?, ?,\n" +
            "      ?, ?, ?, ?, ?, ?, ?,\n" +
            "      ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_ORDER_STATUS = "INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS)\n" +
            "    VALUES (?, ?, ?, ?)";
}
