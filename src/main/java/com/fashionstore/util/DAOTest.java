package com.fashionstore.util;

import java.util.List;

import com.fashionstore.dao.CategoriesDAO;
import com.fashionstore.dao.ProductsDAO;
import com.fashionstore.dao.ProductVariantsDAO;
import com.fashionstore.dao.CartDAO;
import com.fashionstore.dao.CartItemsDAO;
import com.fashionstore.dao.OrdersDAO;
import com.fashionstore.dao.OrderItemsDAO;

import com.fashionstore.dao.impl.CategoriesDAOImpl;
import com.fashionstore.dao.impl.ProductsDAOImpl;
import com.fashionstore.dao.impl.ProductVariantsDAOImpl;
import com.fashionstore.dao.impl.CartDAOImpl;
import com.fashionstore.dao.impl.CartItemsDAOImpl;
import com.fashionstore.dao.impl.OrdersDAOImpl;
import com.fashionstore.dao.impl.OrderItemsDAOImpl;

import com.fashionstore.model.Categories;
import com.fashionstore.model.Products;
import com.fashionstore.model.ProductVariants;
import com.fashionstore.model.Cart;
import com.fashionstore.model.CartItems;
import com.fashionstore.model.Orders;
import com.fashionstore.model.OrderItems;

public class DAOTest {

    public static void main(String[] args) {

        CategoriesDAO categoryDAO = new CategoriesDAOImpl();
        ProductsDAO productDAO = new ProductsDAOImpl();
        ProductVariantsDAO variantDAO = new ProductVariantsDAOImpl();
        CartDAO cartDAO = new CartDAOImpl();
        CartItemsDAO cartItemsDAO = new CartItemsDAOImpl();
        OrdersDAO ordersDAO = new OrdersDAOImpl();
        OrderItemsDAO orderItemsDAO = new OrderItemsDAOImpl();

        // ================= CATEGORY TEST =================
        System.out.println("========= CATEGORY TEST =========");

        List<Categories> categories =
                categoryDAO.getAllCategories();

        for (Categories category : categories) {
            System.out.println(
                    category.getCategoryId() + " | " +
                    category.getCategoryName() + " | " +
                    category.getDescription()
            );
        }


        // ================= PRODUCT TEST =================
        System.out.println("\n========= PRODUCT TEST =========");

        List<Products> products =
                productDAO.getAllProducts();

        for (Products product : products) {
            System.out.println(
                    product.getProductId() + " | " +
                    product.getProductName() + " | " +
                    product.getBrand() + " | " +
                    product.getPrice()
            );
        }


        // ================= PRODUCT VARIANT TEST =================
        System.out.println("\n========= PRODUCT VARIANT TEST =========");

        List<ProductVariants> variants =
                variantDAO.getVariantsByProductId(1);

        for (ProductVariants variant : variants) {
            System.out.println(
                    variant.getVariantId() + " | " +
                    variant.getSize() + " | " +
                    variant.getStockQuantity()
            );
        }


        // ================= CART TEST =================
        System.out.println("\n========= CART TEST =========");

        Cart cart = cartDAO.getCartByUserId(1);

        if (cart != null) {
            System.out.println(
                    "Cart ID: " + cart.getCartId()
            );

            System.out.println(
                    "User ID: " + cart.getUserId()
            );
        }


        // ================= CART ITEMS TEST =================
        System.out.println("\n========= CART ITEMS TEST =========");

        List<CartItems> cartItems =
                cartItemsDAO.getCartItemsByCartId(1);

        for (CartItems item : cartItems) {
            System.out.println(
                    item.getCartItemId() + " | " +
                    item.getVariantId() + " | " +
                    item.getQuantity()
            );
        }


        // ================= ORDERS TEST =================
        System.out.println("\n========= ORDERS TEST =========");

        List<Orders> orders =
                ordersDAO.getOrdersByUserId(1);

        for (Orders order : orders) {
            System.out.println(
                    order.getOrderId() + " | " +
                    order.getTotalAmount() + " | " +
                    order.getOrderStatus()
            );
        }


        // ================= ORDER ITEMS TEST =================
        System.out.println("\n========= ORDER ITEMS TEST =========");

        List<OrderItems> orderItems =
                orderItemsDAO.getOrderItemsByOrderId(1);

        for (OrderItems item : orderItems) {
            System.out.println(
                    item.getOrderItemId() + " | " +
                    item.getVariantId() + " | " +
                    item.getQuantity() + " | " +
                    item.getPrice()
            );
        }

        System.out.println("\n========= ALL DAO TESTS COMPLETED =========");
    }
}