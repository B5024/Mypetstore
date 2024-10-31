package mypetstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 6620528781626504362L;

    private Item item;
    private int quantity;      //购物车内该商品的数量
    private boolean inStock;      //该商品目前是否有货
    private BigDecimal total;         //该商品总价（listPrice * quantity）

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        calculateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }

    //向购物车中添加商品，购物车内该商品数量增加1，并计算该商品的总价
    public void incrementQuantity() {
        quantity++;
        calculateTotal();
    }

    //计算购物车内某商品总价
    private void calculateTotal() {
        if (item != null && item.getListPrice() != null) {
            total = item.getListPrice().multiply(new BigDecimal(quantity));
        } else {
            total = null;
        }
    }

}
