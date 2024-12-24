package csu.mypetstoree.persistence.impl;

import csu.mypetstoree.domain.Account;
import csu.mypetstoree.domain.Logs;
import csu.mypetstoree.persistence.DBUtil;
import csu.mypetstoree.persistence.LogsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class LogsDaoImpl implements LogsDao {

    public void insertLoginLogs(Logs loginLogs){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_LOGIN_LOGS);
            Timestamp timestamp = Timestamp.valueOf(loginLogs.getLogindate());
            statement.setString(1, loginLogs.getUsername());
            statement.setTimestamp(2, timestamp);
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("Login logs Insert succeed");
            }else {
                System.out.println("Login logs Insert f ail");
            }
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertCartLogs(Logs cartLogs){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_CART_LOGS);
            Timestamp timestamp = Timestamp.valueOf(cartLogs.getLogindate());
            statement.setString(1, cartLogs.getUsername());
            statement.setString(2, cartLogs.getAction());
            statement.setString(3, cartLogs.getItemId());
            statement.setTimestamp(4, timestamp);
            int rows = statement.executeUpdate();
            if(rows == 1){
                System.out.println("Cart logs Insert succeed");
            }else {
                System.out.println("Cart logs Insert fail");
            }
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getLoginLogs(String username){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_LOGIN_LOGS);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                System.out.println("Login logs of " + username +":");
                do{
                    System.out.println("login in on " + resultSet.getTimestamp("Date").toString());
                }while(resultSet.next());
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getCartLogs(String username){
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_CART_LOGS);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                System.out.println("Cart logs of " + username +":");
                do{
                    System.out.println(resultSet.getTimestamp("Date").toString() + " " + resultSet.getString("Action") + " " +
                            resultSet.getString("itemid") + " to the cart");
                }while(resultSet.next());
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(statement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static final String INSERT_LOGIN_LOGS = "INSERT INTO LOGINLOGS\n" +
            "      (USERNAME,DATE)\n" +
            "    VALUES\n" +
            "      (?, ?)";

    private static final String GET_LOGIN_LOGS = "SELECT\n" +
            "    DATE\n" +
            "    FROM LOGINLOGS\n" +
            "    WHERE USERNAME = ?\n";

    private static final String INSERT_CART_LOGS = "INSERT INTO CARTLOGS\n" +
            "      (USERNAME,ACTION,ITEMID,DATE)\n" +
            "    VALUES\n" +
            "      (?, ?, ?, ?)";

    private static final String GET_CART_LOGS = "SELECT\n" +
            "    DATE,\n" +
            "    ACTION,\n" +
            "    ITEMID\n" +
            "    FROM CARTLOGS\n" +
            "    WHERE USERNAME = ?\n";
}
