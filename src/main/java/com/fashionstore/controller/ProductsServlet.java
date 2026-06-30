package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.ProductsDAO;
import com.fashionstore.dao.impl.ProductsDAOImpl;
import com.fashionstore.model.Products;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductsDAO productsDAO;

    @Override
    public void init() throws ServletException {

        productsDAO = new ProductsDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // GET FILTER VALUES

        String keyword =
                request.getParameter("keyword");

        String categoryIdStr =
                request.getParameter("categoryId");

        String minPriceStr =
                request.getParameter("minPrice");

        String maxPriceStr =
                request.getParameter("maxPrice");

        String sortBy =
                request.getParameter("sortBy");

        // DEFAULT VALUES

        int categoryId = 0;

        double minPrice = 0;

        double maxPrice = 0;

        // CONVERT STRING TO NUMBER

        try {

            if (categoryIdStr != null
                    && !categoryIdStr.isEmpty()) {

                categoryId =
                        Integer.parseInt(categoryIdStr);
            }

            if (minPriceStr != null
                    && !minPriceStr.isEmpty()) {

                minPrice =
                        Double.parseDouble(minPriceStr);
            }

            if (maxPriceStr != null
                    && !maxPriceStr.isEmpty()) {

                maxPrice =
                        Double.parseDouble(maxPriceStr);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        // GET FILTERED PRODUCTS

        List<Products> productList =
                productsDAO.filterProducts(
                        keyword,
                        categoryId,
                        minPrice,
                        maxPrice,
                        sortBy
                );

        // SEND DATA TO JSP

        request.setAttribute(
                "productList",
                productList
        );

        // OPEN PRODUCTS PAGE

        request.getRequestDispatcher(
                "/WEB-INF/views/partial/products.jsp")
                .forward(request, response);
    }
}