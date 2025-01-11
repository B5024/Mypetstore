package csu.mypetstoree.persistence.impl;

import csu.mypetstoree.domain.Product;
import csu.mypetstoree.persistence.DBUtil;
import csu.mypetstoree.persistence.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    public static final String GET_PRODUCT_LIST_BY_CATEGORY =
            "SELECT PRODUCTID,NAME,DESCN AS description,CATEGORY AS categoryId FROM PRODUCT WHERE CATEGORY = ?";
    public static final String GET_PRODUCT =
            "SELECT PRODUCTID,NAME,DESCN AS description,CATEGORY AS categoryId FROM PRODUCT WHERE PRODUCTID = ?";
    public static final String SEARCH_PRODUCT_LIST =
            "SELECT PRODUCTID,NAME,DESCN AS description,CATEGORY AS categoryId FROM PRODUCT WHERE lower(name) like ?";

    public static final String GET_PRODUCT_BY_NAME =
            "SELECT PRODUCTID,NAME,DESCN AS description,CATEGORY AS categoryId FROM PRODUCT WHERE NAME = ?";

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> productList = new ArrayList<Product>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_PRODUCT_LIST_BY_CATEGORY);
            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setProductId(rs.getString("PRODUCTID"));
                product.setName(rs.getString("NAME"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getString("categoryId"));
                productList.add(product);
            }
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_PRODUCT);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                product = new Product();
                product.setProductId(rs.getString("PRODUCTID"));
                product.setName(rs.getString("NAME"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getString("categoryId"));
            }
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(SEARCH_PRODUCT_LIST);
            ps.setString(1, keywords);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setProductId(rs.getString(1));
                product.setName(rs.getString(2));
                product.setDescription(rs.getString(3));
                product.setCategoryId(rs.getString(4));
                productList.add(product);
            }
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProductByName(String name) {
        Product product = null;
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_PRODUCT_BY_NAME);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                product = new Product();
                product.setProductId(rs.getString("PRODUCTID"));
                product.setName(rs.getString("NAME"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getString("categoryId"));
            }
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
