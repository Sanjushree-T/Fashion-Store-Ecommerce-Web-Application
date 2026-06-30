package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.impl.UsersDAOImpl;
import com.fashionstore.model.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // OPEN REGISTER PAGE
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/partial/register.jsp")
                .forward(request, response);
    }

    // REGISTER LOGIC
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Users user = new Users();

        user.setFullName(request.getParameter("fullName"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setPassword(request.getParameter("password"));
        user.setAddress1(request.getParameter("address1"));
        user.setAddress2(request.getParameter("address2"));
        user.setCity(request.getParameter("city"));
        user.setState(request.getParameter("state"));
        user.setPincode(request.getParameter("pincode"));
        user.setCountry(request.getParameter("country"));

        UsersDAOImpl dao = new UsersDAOImpl();

        boolean status = dao.registerUser(user);

        if (status) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

        } else {

            request.setAttribute(
                    "errorMessage",
                    "Registration failed. Please try again.");

            request.getRequestDispatcher(
                    "/WEB-INF/views/partial/register.jsp")
                    .forward(request, response);
        }
    }
}