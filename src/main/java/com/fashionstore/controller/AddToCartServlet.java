package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.impl.CartItemsDAOImpl;
import com.fashionstore.model.CartItems;
import com.fashionstore.model.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        Users user =
                (Users) session.getAttribute("loggedUser");

        if(user == null) {

            response.getWriter().println(
            "Please Login First");

            return;
        }

        int productId =
                Integer.parseInt(
                request.getParameter("productId"));

        CartItems cartItem =
                new CartItems();

        cartItem.setCartId(1);

        cartItem.setVariantId(
                productId);

        cartItem.setQuantity(1);

        CartItemsDAOImpl dao =
                new CartItemsDAOImpl();

        dao.addCartItem(cartItem);

        response.sendRedirect("cart");
    }
}