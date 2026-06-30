package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.impl.CartItemsDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove-cart-item")
public class RemoveCartItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));

        CartItemsDAOImpl dao = new CartItemsDAOImpl();

        dao.removeCartItem(cartItemId);

        response.sendRedirect("cart");
    }
}