package csu.mypetstoree.persistence.impl;

import com.mysql.cj.util.StringInspector;
import csu.mypetstoree.domain.CartItem;
import csu.mypetstoree.domain.Item;
import csu.mypetstoree.persistence.CartItemDao;
import csu.mypetstoree.persistence.DBUtil;
import csu.mypetstoree.service.CatalogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDaoImpl implements CartItemDao {
    private static String GET_CATEGORY_LIST =
            "SELECT ITEMID,INSTOCK,QUANTITY FROM ";
//CARTITEM
    private static final String INSERT_CATE_ITEM_TOP =
            "INSERT INTO ";
    private static final String INSERT_CATE_ITEM_BUTTON = "_CARTITEM (ITEMID, INSTOCK,QUANTITY) VALUES (?, ?, ?)";



    @Override
    public List<CartItem> getCartItems(String username) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        //循环使用了
        CatalogService catalogService = new CatalogService();
        try {
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_CATEGORY_LIST+username+"_CARTITEM");
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
        try{
            Connection conn = DBUtil.getConnection();
            System.out.println(username);
            PreparedStatement stmt = conn.prepareStatement(INSERT_CATE_ITEM_TOP+username+INSERT_CATE_ITEM_BUTTON);
            stmt.setString(1, cartItem.getItem().getItemId());
            stmt.setBoolean(2, cartItem.isInStock());
            stmt.setInt(3, cartItem.getQuantity());
            System.out.println("Adding " + cartItem.getItem().getItemId()+":"+cartItem.getItem().getQuantity()+":"+cartItem.isInStock());
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
