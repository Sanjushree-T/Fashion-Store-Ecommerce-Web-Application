package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CartItemsDAO;
import com.fashionstore.model.CartItems;
import com.fashionstore.util.DBConnection;

public class CartItemsDAOImpl implements CartItemsDAO {

    private Connection connection;

    // ADD CART ITEM
    private static final String ADD_CART_ITEM_SQL = """
            INSERT INTO cart_items
            (cart_id, variant_id, quantity)
            VALUES (?, ?, ?)
            """;

    // UPDATE CART ITEM QUANTITY
    private static final String UPDATE_CART_ITEM_SQL = """
            UPDATE cart_items
            SET quantity = ?
            WHERE cart_item_id = ?
            """;

    // REMOVE CART ITEM
    private static final String REMOVE_CART_ITEM_SQL = """
            DELETE FROM cart_items
            WHERE cart_item_id = ?
            """;

    // GET ITEMS BY CART ID
    private static final String GET_CART_ITEMS_SQL = """
            SELECT * FROM cart_items
            WHERE cart_id = ?
            """;

    // CLEAR CART
    private static final String CLEAR_CART_SQL = """
            DELETE FROM cart_items
            WHERE cart_id = ?
            """;

    public CartItemsDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // COMMON METHOD
    private CartItems extractCartItemFromResultSet(ResultSet rs)
            throws Exception {

        CartItems cartItem = new CartItems();

        cartItem.setCartItemId(rs.getInt("cart_item_id"));
        cartItem.setCartId(rs.getInt("cart_id"));
        cartItem.setVariantId(rs.getInt("variant_id"));
        cartItem.setQuantity(rs.getInt("quantity"));

        return cartItem;
    }

    // ADD CART ITEM
    @Override
    public boolean addCartItem(CartItems cartItem) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(ADD_CART_ITEM_SQL);

            pstmt.setInt(1, cartItem.getCartId());
            pstmt.setInt(2, cartItem.getVariantId());
            pstmt.setInt(3, cartItem.getQuantity());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // UPDATE CART ITEM QUANTITY
    @Override
    public boolean updateCartItemQuantity(int cartItemId, int quantity) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_CART_ITEM_SQL);

            pstmt.setInt(1, quantity);
            pstmt.setInt(2, cartItemId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // REMOVE CART ITEM
    @Override
    public boolean removeCartItem(int cartItemId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(REMOVE_CART_ITEM_SQL);

            pstmt.setInt(1, cartItemId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET CART ITEMS BY CART ID
    @Override
    public List<CartItems> getCartItemsByCartId(int cartId) {

        List<CartItems> cartItemsList = new ArrayList<>();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(GET_CART_ITEMS_SQL);

            pstmt.setInt(1, cartId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                cartItemsList.add(
                        extractCartItemFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartItemsList;
    }

    // CLEAR CART
    @Override
    public boolean clearCart(int cartId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(CLEAR_CART_SQL);

            pstmt.setInt(1, cartId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
