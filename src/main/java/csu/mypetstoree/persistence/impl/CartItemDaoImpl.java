package csu.mypetstoree.persistence.impl;

import csu.mypetstoree.domain.CartItem;
import csu.mypetstoree.domain.Item;
import csu.mypetstoree.domain.Product;
import csu.mypetstoree.persistence.CartItemDao;
import csu.mypetstoree.persistence.DBUtil;
import csu.mypetstoree.service.CatalogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDaoImpl implements CartItemDao {
    private static String GET_CATEGORY_LIST_TOP= "SELECT ITEMID,INSTOCK,QUANTITY FROM ";
    private static String GET_CATEGORY_LIST_BUTTON= "_CARTITEM";

    private static final String INSERT_CATE_ITEM_TOP = "INSERT INTO ";
    private static final String INSERT_CATE_ITEM_BUTTON = "_CARTITEM (ITEMID, INSTOCK,QUANTITY) VALUES (?, ?, ?)";

    private static final String INSERT_CATE_ITEM_NUMBER_TOP = "UPDATE ";
    private static final String INSERT_CATE_ITEM_NUMBER_BUTTON = "_CARTITEM SET QUANTITY = ? WHERE ITEMID = ?";

    private static final String DELETE_CATE_ITEM_TOP = "DELETE FROM ";
    private static final String DELETE_CATE_ITEM_BUTTON = "_CARTITEM WHERE ITEMID = ?";

    private static final String SQL_ITEMID_TOP = "SELECT QUANTITY FROM ";
    private static final String SQL_ITEMID_BOTTOM = "_CARTITEM WHERE itemid = ?";

    @Override
    public List<CartItem> getCartItems(String username) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        //循环使用了
        CatalogService catalogService = new CatalogService();
        try {
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_CATEGORY_LIST_TOP+username+"_CARTITEM");
            while (rs.next()) {
                String itemId = rs.getString("ITEMID");
                Item item = catalogService.getItem(itemId);
                int quantity = rs.getInt("QUANTITY");
                boolean inStock = rs.getBoolean("INSTOCK");

                CartItem cartItem = new CartItem();
                cartItem.setItem(item);
                cartItem.setQuantity(quantity);
                cartItem.setInStock(inStock);
                cartItems.add(cartItem);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    @Override
    public void addCartItem(CartItem cartItem, String username) {
        CatalogService catalogService = new CatalogService();
        if(isItemExist(username,cartItem.getItem().getItemId())){
            System.out.println("Item already exists");
            //这里已经存在了 要get对应cartItem的对象出来
            try{
                CartItem cartItem1 = getCartItem(username,cartItem.getItem().getItemId());
                Connection conn = DBUtil.getConnection();
                System.out.println(cartItem1.getQuantity()+1);
                PreparedStatement stmt = conn.prepareStatement(INSERT_CATE_ITEM_NUMBER_TOP+username+INSERT_CATE_ITEM_NUMBER_BUTTON);
                stmt.setInt(1,cartItem1.getQuantity()+1);
                stmt.setString(2,cartItem.getItem().getItemId());

                int rows = stmt.executeUpdate();
                if(rows == 1){
                    System.out.println("CartItemnuber Insert succeed");
                }else {
                    System.out.println("CartItemnuber Insert fail");
                }
                stmt.close();
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }else {
            try{
                Connection conn = DBUtil.getConnection();
                System.out.println(username);
                //查数据库里面是否有
                PreparedStatement stmt = conn.prepareStatement(INSERT_CATE_ITEM_TOP+username+INSERT_CATE_ITEM_BUTTON);
//                if(isItemExist(cartItem.getItem().getItemId(),username))
                stmt.setString(1, cartItem.getItem().getItemId());
                stmt.setBoolean(2, cartItem.isInStock());
                stmt.setInt(3, cartItem.getQuantity());
//            System.out.println("Adding " + cartItem.getItem().getItemId()+":"+cartItem.getQuantity()+":"+cartItem.isInStock());
                //这里添加当前账户 多一个参数get账户进来
                int rows = stmt.executeUpdate();
                if(rows == 1){
                    System.out.println("CartItem Insert succeed");
                }else {
                    System.out.println("CartItem Insert fail");
                }
                stmt.close();
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeItemById(String itemId, String username) {
        CatalogService catalogService = new CatalogService();
        try {
            Connection conn = DBUtil.getConnection();
            System.out.println(DELETE_CATE_ITEM_TOP+username+DELETE_CATE_ITEM_BUTTON);
            PreparedStatement stmt = conn.prepareStatement(DELETE_CATE_ITEM_TOP+username+DELETE_CATE_ITEM_BUTTON);
            stmt.setString(1, itemId);
            int rows = stmt.executeUpdate();
            if(rows == 1){
                System.out.println("CartItem Delete succeed");
            }else{
                System.out.println("CartItem Delete fail");
            }
            stmt.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public CartItem getCartItem(String username, String itemId) {
        CartItem cartItem = new CartItem();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_ITEMID_TOP + username + SQL_ITEMID_BOTTOM);
            stmt.setString(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cartItem.setQuantity(rs.getInt("QUANTITY"));
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItem;
    }

    @Override
    public boolean isItemExist( String username,String itemId) {
        CatalogService catalogService = new CatalogService();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_ITEMID_TOP + username + SQL_ITEMID_BOTTOM);
            stmt.setString(1, itemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); // 获取 COUNT(*) 的结果
                    return count > 0; // 如果返回的数量大于 0，则表示存在该 itemid
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
