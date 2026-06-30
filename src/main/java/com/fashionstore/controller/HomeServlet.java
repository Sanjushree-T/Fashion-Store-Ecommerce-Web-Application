package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import com.fashionstore.dao.CategoriesDAO;
import com.fashionstore.dao.ProductsDAO;
import com.fashionstore.dao.impl.CategoriesDAOImpl;
import com.fashionstore.dao.impl.ProductsDAOImpl;
import com.fashionstore.model.Categories;
import com.fashionstore.model.Products;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // DAO objects
    private final CategoriesDAO categoriesDAO = new CategoriesDAOImpl();
    private final ProductsDAO productsDAO = new ProductsDAOImpl();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch all categories
        List<Categories> categories =
                categoriesDAO.getAllCategories();

        // Fetch all products
        List<Products> products =
                productsDAO.getAllProducts();

        // Send data to JSP
        request.setAttribute("categories", categories);
        request.setAttribute("products", products);

        // Redirect to homepage JSP
        request.getRequestDispatcher(
                "/WEB-INF/views/partial/home.jsp")
                .forward(request, response);
    }
}