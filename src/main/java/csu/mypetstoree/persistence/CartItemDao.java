package csu.mypetstoree.persistence;

import csu.mypetstoree.domain.CartItem;

import java.util.List;

public interface CartItemDao {
    List<CartItem> getCartItems(String username);

    void addCartItem(CartItem cartItem, String username);

    void removeItemById(String itemId,String username);

    boolean isItemExist( String username,String itemId);

    CartItem getCartItem(String username, String itemId);


}
