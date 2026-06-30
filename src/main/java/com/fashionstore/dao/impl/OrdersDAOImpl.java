package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.OrdersDAO;
import com.fashionstore.model.Orders;
import com.fashionstore.util.DBConnection;

public class OrdersDAOImpl implements OrdersDAO {

    private Connection connection;

    // PLACE ORDER
    private static final String PLACE_ORDER_SQL = """
            INSERT INTO orders (
                user_id,
                total_amount,
                payment_method,
                order_status,
                delivery_name,
                delivery_phone,
                delivery_address1,
                delivery_address2,
                delivery_city,
                delivery_pincode,
                delivery_country
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    // GET ORDER BY ID
    private static final String GET_ORDER_BY_ID_SQL = """
            SELECT * FROM orders
            WHERE order_id = ?
            """;

    // GET ORDERS BY USER ID
    private static final String GET_ORDERS_BY_USER_SQL = """
            SELECT * FROM orders
            WHERE user_id = ?
            ORDER BY order_date DESC
            """;

    // GET ALL ORDERS
    private static final String GET_ALL_ORDERS_SQL = """
            SELECT * FROM orders
            ORDER BY order_date DESC
            """;

    // UPDATE ORDER STATUS
    private static final String UPDATE_ORDER_STATUS_SQL = """
            UPDATE orders
            SET order_status = ?
            WHERE order_id = ?
            """;

    public OrdersDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // COMMON METHOD
    private Orders extractOrderFromResultSet(ResultSet rs)
            throws Exception {

        Orders order = new Orders();

        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setOrderDate(rs.getTimestamp("order_date"));

        // BigDecimal fix
        order.setTotalAmount(
                rs.getBigDecimal("total_amount"));

        order.setPaymentMethod(
                rs.getString("payment_method"));

        order.setOrderStatus(
                rs.getString("order_status"));

        order.setDeliveryName(
                rs.getString("delivery_name"));

        order.setDeliveryPhone(
                rs.getString("delivery_phone"));

        order.setDeliveryAddress1(
                rs.getString("delivery_address1"));

        order.setDeliveryAddress2(
                rs.getString("delivery_address2"));

        order.setDeliveryCity(
                rs.getString("delivery_city"));

        order.setDeliveryPincode(
                rs.getString("delivery_pincode"));

        order.setDeliveryCountry(
                rs.getString("delivery_country"));

        return order;
    }

    // PLACE ORDER
    @Override
    public boolean placeOrder(Orders order) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            PLACE_ORDER_SQL);

            pstmt.setInt(1, order.getUserId());

            // BigDecimal fix
            pstmt.setBigDecimal(
                    2,
                    order.getTotalAmount());

            pstmt.setString(
                    3,
                    order.getPaymentMethod());

            pstmt.setString(
                    4,
                    order.getOrderStatus());

            pstmt.setString(
                    5,
                    order.getDeliveryName());

            pstmt.setString(
                    6,
                    order.getDeliveryPhone());

            pstmt.setString(
                    7,
                    order.getDeliveryAddress1());

            pstmt.setString(
                    8,
                    order.getDeliveryAddress2());

            pstmt.setString(
                    9,
                    order.getDeliveryCity());

            pstmt.setString(
                    10,
                    order.getDeliveryPincode());

            pstmt.setString(
                    11,
                    order.getDeliveryCountry());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET ORDER BY ID
    @Override
    public Orders getOrderById(int orderId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            GET_ORDER_BY_ID_SQL);

            pstmt.setInt(1, orderId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractOrderFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // GET ORDERS BY USER ID
    @Override
    public List<Orders> getOrdersByUserId(int userId) {

        List<Orders> ordersList =
                new ArrayList<>();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            GET_ORDERS_BY_USER_SQL);

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ordersList.add(
                        extractOrderFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ordersList;
    }

    // GET ALL ORDERS
    @Override
    public List<Orders> getAllOrders() {

        List<Orders> ordersList =
                new ArrayList<>();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            GET_ALL_ORDERS_SQL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ordersList.add(
                        extractOrderFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ordersList;
    }

    // UPDATE ORDER STATUS
    @Override
    public boolean updateOrderStatus(
            int orderId,
            String orderStatus) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            UPDATE_ORDER_STATUS_SQL);

            pstmt.setString(1, orderStatus);
            pstmt.setInt(2, orderId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}