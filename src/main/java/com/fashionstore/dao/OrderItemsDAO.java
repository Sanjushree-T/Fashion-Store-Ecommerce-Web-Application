package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.OrderItems;

public interface OrderItemsDAO {

    boolean addOrderItem(OrderItems orderItem);

    List<OrderItems> getOrderItemsByOrderId(int orderId);

    boolean updateOrderItem(OrderItems orderItem);

    boolean deleteOrderItem(int orderItemId);
}
