package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.impl.CartItemsDAOImpl;
import com.fashionstore.model.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

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

            response.sendRedirect("login");

            return;
        }

        // DELIVERY DETAILS

        String deliveryName =
                request.getParameter("deliveryName");

        String deliveryPhone =
                request.getParameter("deliveryPhone");

        String deliveryAddress1 =
                request.getParameter("deliveryAddress1");

        String deliveryAddress2 =
                request.getParameter("deliveryAddress2");

        String deliveryCity =
                request.getParameter("deliveryCity");

        String deliveryPincode =
                request.getParameter("deliveryPincode");

        String deliveryCountry =
                request.getParameter("deliveryCountry");

        String paymentMethod =
                request.getParameter("paymentMethod");

        // TEMPORARY LOGIC

        // CLEAR CART AFTER ORDER

        CartItemsDAOImpl cartDAO =
                new CartItemsDAOImpl();

        cartDAO.clearCart(1);

        // SEND DATA TO SUCCESS PAGE

        request.setAttribute(
                "deliveryName",
                deliveryName);

        request.setAttribute(
                "paymentMethod",
                paymentMethod);

        request.getRequestDispatcher(
                "/WEB-INF/views/partial/order-success.jsp")
                .forward(request, response);
    }
}
