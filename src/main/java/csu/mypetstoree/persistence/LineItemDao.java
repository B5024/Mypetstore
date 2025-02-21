package csu.mypetstoree.persistence;

import csu.mypetstoree.domain.LineItem;

import java.util.List;

public interface LineItemDao {

    List<LineItem> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItem lineItem);

}
