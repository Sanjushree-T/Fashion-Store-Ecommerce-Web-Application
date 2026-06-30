package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.CartItems;

public interface CartItemsDAO {

    boolean addCartItem(CartItems cartItem);

    boolean updateCartItemQuantity(int cartItemId, int quantity);

    boolean removeCartItem(int cartItemId);

    List<CartItems> getCartItemsByCartId(int cartId);

    boolean clearCart(int cartId);
}
