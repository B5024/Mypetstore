package csu.mypetstoree.service;


import csu.mypetstoree.domain.CartItem;
import csu.mypetstoree.domain.Category;
import csu.mypetstoree.domain.Product;
import csu.mypetstoree.domain.Item;
import csu.mypetstoree.persistence.CartItemDao;
import csu.mypetstoree.persistence.CategoryDao;
import csu.mypetstoree.persistence.ItemDao;
import csu.mypetstoree.persistence.ProductDao;
import csu.mypetstoree.persistence.impl.CartItemDaoImpl;
import csu.mypetstoree.persistence.impl.CategoryDaoImpl;
import csu.mypetstoree.persistence.impl.ItemDaoImpl;
import csu.mypetstoree.persistence.impl.ProductDaoImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class CatalogService {
    private CategoryDao categoryDao;
    private ProductDao productDao;
    private ItemDao itemDao;
    private CartItemDao cartItemDao;
    public CatalogService() {
        this.categoryDao = new CategoryDaoImpl();
        this.productDao = new ProductDaoImpl();
        this.itemDao = new ItemDaoImpl();
        this.cartItemDao = new CartItemDaoImpl();
    }

    public List<Category> getCategoryList() throws SQLException {
        return categoryDao.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return productDao.getProductListByCategory(categoryId);
    }

    public Product getProductByName(String name){
        return productDao.getProductByName(name);
    }

    public List<Product> searchProductList(String keyword) {
        return productDao.searchProductList("%" + keyword.toLowerCase() + "%");
    }

    public List<Item> getItemListByProduct(String productId) {
        return itemDao.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDao.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDao.getInventoryQuantity(itemId) > 0;
    }

    public List<CartItem> getCartItemList(String username) {
        return cartItemDao.getCartItems(username);
    }
    public void addCartItem(CartItem cartItem,String username) {
        cartItemDao.addCartItem(cartItem,username);
    }
    public void removeItemById(String itemId,String username) {
        cartItemDao.removeItemById(itemId,username);
    }
    public void NewCartItemTable(String username) {
        cartItemDao.NewCartItemTable(username);
    }
    public void UpdateCartItem(String username,String itemId, int quantity) {
        if(quantity == 0) {
            cartItemDao.removeItemById(itemId,username);
        }else {
            System.out.println("Yes");
            cartItemDao.updateCartItem(username,itemId,quantity);
        }
    }
}


