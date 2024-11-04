package csu.mypetstoree.persistence.impl;

import csu.mypetstoree.domain.CartItem;
import csu.mypetstoree.domain.Item;
import csu.mypetstoree.persistence.CartItemDao;
import csu.mypetstoree.persistence.DBUtil;
import csu.mypetstoree.service.CatalogService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartItemDaoImpl implements CartItemDao {
    private static final String GET_CATEGORY_LIST =
            "SELECT ITEMID,INSTOCK,QUANTITY FROM CARTITEM";

    @Override
    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        //循环使用了
        CatalogService catalogService = new CatalogService();
        try {
            Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_CATEGORY_LIST);
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
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
}
