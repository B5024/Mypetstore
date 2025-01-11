package csu.mypetstoree.persistence.impl;

import csu.mypetstoree.domain.LineItem;
import csu.mypetstoree.persistence.DBUtil;
import csu.mypetstoree.persistence.LineItemDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoImpl implements LineItemDao {

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItems = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_LINE_ITEMS_BY_ORDER_ID);
            statement.setString(1, "orderId");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                lineItems = new ArrayList<>();
                do{
                    LineItem lineItem = new LineItem();
                    lineItem.setOrderId(resultSet.getInt("orderId"));
                    lineItem.setLineNumber(resultSet.getInt("lineNumber"));
                    lineItem.setItemId(resultSet.getString("itemId"));
                    lineItem.setQuantity(resultSet.getInt("quantity"));
                    lineItem.setUnitPrice(resultSet.getBigDecimal("unitPrice"));

                    lineItems.add(lineItem);
                }while(resultSet.next());
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return lineItems;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_LINE_ITEM);
            statement.setInt(1, lineItem.getOrderId());
            statement.setInt(2, lineItem.getLineNumber());
            statement.setString(3, lineItem.getItemId());
            statement.setInt(4, lineItem.getQuantity());
            statement.setBigDecimal(5, lineItem.getUnitPrice());
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("LineItem Insert succeed");
            }else {
                System.out.println("LineItem Insert fail");
            }
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static final String GET_LINE_ITEMS_BY_ORDER_ID = "SELECT\n" +
            "      ORDERID,\n" +
            "      LINENUM AS lineNumber,\n" +
            "      ITEMID,\n" +
            "      QUANTITY,\n" +
            "      UNITPRICE\n" +
            "    FROM LINEITEM\n" +
            "    WHERE ORDERID = ?";

    private static final String INSERT_LINE_ITEM = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE)\n" +
            "    VALUES (?, ?, ?, ?, ?)";
}
