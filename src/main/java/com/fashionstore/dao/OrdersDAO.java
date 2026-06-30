package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.Orders;

public interface OrdersDAO {

    boolean placeOrder(Orders order);

    Orders getOrderById(int orderId);

    List<Orders> getOrdersByUserId(int userId);

    List<Orders> getAllOrders();

    boolean updateOrderStatus(int orderId, String orderStatus);
}
