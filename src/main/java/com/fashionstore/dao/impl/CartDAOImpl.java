package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.Cart;
import com.fashionstore.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    private Connection connection;

    // CREATE CART
    private static final String CREATE_CART_SQL = """
            INSERT INTO cart (user_id)
            VALUES (?)
            """;

    // GET CART BY USER ID
    private static final String GET_CART_BY_USER_SQL = """
            SELECT * FROM cart
            WHERE user_id = ?
            """;

    // DELETE CART
    private static final String DELETE_CART_SQL = """
            DELETE FROM cart
            WHERE cart_id = ?
            """;

    public CartDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // COMMON METHOD
    private Cart extractCartFromResultSet(ResultSet rs)
            throws Exception {

        Cart cart = new Cart();

        cart.setCartId(rs.getInt("cart_id"));
        cart.setUserId(rs.getInt("user_id"));
        cart.setCreatedAt(rs.getTimestamp("created_at"));

        return cart;
    }

    // CREATE CART
    @Override
    public boolean createCart(Cart cart) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(CREATE_CART_SQL);

            pstmt.setInt(1, cart.getUserId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET CART BY USER ID
    @Override
    public Cart getCartByUserId(int userId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(GET_CART_BY_USER_SQL);

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractCartFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // DELETE CART
    @Override
    public boolean deleteCart(int cartId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_CART_SQL);

            pstmt.setInt(1, cartId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
