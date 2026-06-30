package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.OrderItemsDAO;
import com.fashionstore.model.OrderItems;
import com.fashionstore.util.DBConnection;

public class OrderItemsDAOImpl implements OrderItemsDAO {

    private Connection connection;

    // ADD ORDER ITEM
    private static final String ADD_ORDER_ITEM_SQL = """
            INSERT INTO order_items
            (order_id, variant_id, quantity, price)
            VALUES (?, ?, ?, ?)
            """;

    // GET ORDER ITEMS BY ORDER ID
    private static final String GET_ORDER_ITEMS_BY_ORDER_SQL = """
            SELECT * FROM order_items
            WHERE order_id = ?
            """;

    // UPDATE ORDER ITEM
    private static final String UPDATE_ORDER_ITEM_SQL = """
            UPDATE order_items
            SET variant_id = ?,
                quantity = ?,
                price = ?
            WHERE order_item_id = ?
            """;

    // DELETE ORDER ITEM
    private static final String DELETE_ORDER_ITEM_SQL = """
            DELETE FROM order_items
            WHERE order_item_id = ?
            """;

    public OrderItemsDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // COMMON METHOD
    private OrderItems extractOrderItemFromResultSet(ResultSet rs)
            throws Exception {

        OrderItems orderItem = new OrderItems();

        orderItem.setOrderItemId(
                rs.getInt("order_item_id"));

        orderItem.setOrderId(
                rs.getInt("order_id"));

        orderItem.setVariantId(
                rs.getInt("variant_id"));

        orderItem.setQuantity(
                rs.getInt("quantity"));

        // BigDecimal fix
        orderItem.setPrice(
                rs.getBigDecimal("price"));

        return orderItem;
    }

    // ADD ORDER ITEM
    @Override
    public boolean addOrderItem(OrderItems orderItem) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            ADD_ORDER_ITEM_SQL);

            pstmt.setInt(
                    1,
                    orderItem.getOrderId());

            pstmt.setInt(
                    2,
                    orderItem.getVariantId());

            pstmt.setInt(
                    3,
                    orderItem.getQuantity());

            // BigDecimal fix
            pstmt.setBigDecimal(
                    4,
                    orderItem.getPrice());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET ORDER ITEMS BY ORDER ID
    @Override
    public List<OrderItems> getOrderItemsByOrderId(
            int orderId) {

        List<OrderItems> orderItemsList =
                new ArrayList<>();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            GET_ORDER_ITEMS_BY_ORDER_SQL);

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                orderItemsList.add(
                        extractOrderItemFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderItemsList;
    }

    // UPDATE ORDER ITEM
    @Override
    public boolean updateOrderItem(
            OrderItems orderItem) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            UPDATE_ORDER_ITEM_SQL);

            pstmt.setInt(
                    1,
                    orderItem.getVariantId());

            pstmt.setInt(
                    2,
                    orderItem.getQuantity());

            // BigDecimal fix
            pstmt.setBigDecimal(
                    3,
                    orderItem.getPrice());

            pstmt.setInt(
                    4,
                    orderItem.getOrderItemId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE ORDER ITEM
    @Override
    public boolean deleteOrderItem(
            int orderItemId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            DELETE_ORDER_ITEM_SQL);

            pstmt.setInt(1, orderItemId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}