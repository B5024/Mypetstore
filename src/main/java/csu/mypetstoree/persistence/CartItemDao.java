package csu.mypetstoree.persistence;

import csu.mypetstoree.domain.CartItem;

import java.util.List;

public interface CartItemDao {
    List<CartItem> getCartItems();

}
