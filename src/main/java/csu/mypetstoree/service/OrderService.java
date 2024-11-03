package csu.mypetstoree.service;

import csu.mypetstoree.domain.Item;
import csu.mypetstoree.domain.LineItem;
import csu.mypetstoree.domain.Order;
import csu.mypetstoree.domain.Sequence;
import csu.mypetstoree.persistence.ItemDao;
import csu.mypetstoree.persistence.LineItemDao;
import csu.mypetstoree.persistence.OrderDao;
import csu.mypetstoree.persistence.impl.ItemDaoImpl;
import csu.mypetstoree.persistence.impl.LineItemDaoImpl;
import csu.mypetstoree.persistence.impl.OrderDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private ItemDao itemMapper;

    private OrderDao orderMapper;

    private SequenceMapper sequenceMapper;

    private LineItemDao lineItemMapper;


    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = Integer.valueOf(lineItem.getQuantity());
            Map<String, Object> param = new HashMap<String, Object>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            itemMapper.updateInventoryQuantity(param);
        }

        orderMapper.insertOrder(order);
        orderMapper.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemMapper.insertLineItem(lineItem);
        }
    }


    public Order getOrder(int orderId) {
        Order order = orderMapper.getOrder(orderId);
        order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = itemMapper.getItem(lineItem.getItemId());
            item.setQuantity(itemMapper.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderMapper.getOrdersByUsername(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = (Sequence) sequenceMapper.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceMapper.updateSequence(parameterObject);
        return sequence.getNextId();
    }

}
