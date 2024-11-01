package csu.mypetstoree.persistence.impl;

import csu.mypetstoree.domain.Item;
import csu.mypetstoree.persistence.ItemDao;

import java.util.List;
import java.util.Map;

public class ItemDaoImpl implements ItemDao {

    public static final String UPDATE_INVENTORY_QUANTITY =
        "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS product.productId,NAME AS product.name," +
                "DESCN AS product.description,CATEGORY AS product.categoryId,STATUS," +
                "ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5" +
                "FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";

    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {

    }

    @Override
    public int getInventoryQuantity(String itemId) {
        return 0;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        return List.of();
    }

    @Override
    public Item getItem(String itemId) {
        return null;
    }
}
