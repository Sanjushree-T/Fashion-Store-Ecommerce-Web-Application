package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.impl.CartItemsDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-cart")
public class UpdateCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        if (quantity < 1) {
            quantity = 1;
        }

        CartItemsDAOImpl dao = new CartItemsDAOImpl();

        dao.updateCartItemQuantity(cartItemId, quantity);

        response.sendRedirect("cart");
    }
}