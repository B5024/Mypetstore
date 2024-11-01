package org.example.mypetstore.persistance;

import java.sql.*;

public class DButil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mypetstore";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        }catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeStatement(Statement stmt) {
        if(stmt != null) {
            try {
                stmt.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void closePreparedStatement(PreparedStatement pre) {
        if(pre != null) {
            try {
                pre.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
