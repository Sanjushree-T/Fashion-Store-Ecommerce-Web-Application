package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.impl.CartItemsDAOImpl;
import com.fashionstore.model.CartItems;
import com.fashionstore.model.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Users user = (Users) session.getAttribute("loggedUser");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        CartItemsDAOImpl dao = new CartItemsDAOImpl();

        List<CartItems> cartList =
        		dao.getCartItemsByCartId(1);

        request.setAttribute("cartList", cartList);

        request.getRequestDispatcher("/WEB-INF/views/partial/cart.jsp")
               .forward(request, response);
    }
}