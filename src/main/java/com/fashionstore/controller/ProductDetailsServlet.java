package com.fashionstore.controller;

import java.io.IOException;

import com.fashionstore.dao.ProductsDAO;
import com.fashionstore.dao.impl.ProductsDAOImpl;
import com.fashionstore.model.Products;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product-details")
public class ProductDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ProductsDAO productsDAO =
            new ProductsDAOImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // GET PRODUCT ID
        int productId =
                Integer.parseInt(
                        request.getParameter("id"));

        // FETCH PRODUCT
        Products product =
                productsDAO.getProductById(productId);

        // SEND TO JSP
        request.setAttribute("product", product);

        // FORWARD TO JSP
        request.getRequestDispatcher(
                "/WEB-INF/views/partial/product-details.jsp")
                .forward(request, response);
    }
}