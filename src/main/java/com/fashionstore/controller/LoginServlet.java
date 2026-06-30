package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.impl.UsersDAOImpl;
import com.fashionstore.model.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // OPEN LOGIN PAGE

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/partial/login.jsp")
                .forward(request, response);
    }

    // LOGIN LOGIC

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        UsersDAOImpl dao =
                new UsersDAOImpl();

        Users user =
                dao.loginUser(email, password);

        // LOGIN SUCCESS

        if (user != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "loggedUser",
                    user);

            session.setAttribute(
                    "successMessage",
                    "Login Successful");

            response.sendRedirect(
                    request.getContextPath()
                    + "/products");

        }

        // LOGIN FAILED

        else {

            request.setAttribute(
                    "errorMessage",
                    "Invalid Email or Password");

            request.getRequestDispatcher(
                    "/WEB-INF/views/partial/login.jsp")
                    .forward(request, response);
        }
    }
}