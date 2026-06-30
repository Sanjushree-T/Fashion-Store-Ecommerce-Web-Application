package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.ProductsDAO;
import com.fashionstore.model.Products;
import com.fashionstore.util.DBConnection;

public class ProductsDAOImpl implements ProductsDAO {

    private Connection connection;

    private static final String ADD_PRODUCT_SQL = """
            INSERT INTO products
            (category_id, product_name, brand, description, price, image_url, is_active)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String GET_PRODUCT_BY_ID_SQL = """
            SELECT * FROM products
            WHERE product_id = ?
            """;

    private static final String GET_ALL_PRODUCTS_SQL = """
            SELECT * FROM products
            WHERE is_active = 1
            """;

    private static final String GET_PRODUCTS_BY_CATEGORY_SQL = """
            SELECT * FROM products
            WHERE category_id = ? AND is_active = 1
            """;

    private static final String SEARCH_PRODUCTS_SQL = """
            SELECT * FROM products
            WHERE is_active = 1
            AND (product_name LIKE ? OR brand LIKE ?)
            """;

    private static final String UPDATE_PRODUCT_SQL = """
            UPDATE products
            SET category_id = ?,
                product_name = ?,
                brand = ?,
                description = ?,
                price = ?,
                image_url = ?,
                is_active = ?
            WHERE product_id = ?
            """;

    private static final String DELETE_PRODUCT_SQL = """
            DELETE FROM products
            WHERE product_id = ?
            """;

    public ProductsDAOImpl() {
        connection = DBConnection.getConnection();
    }

    private Products extractProductFromResultSet(ResultSet rs) throws Exception {

        Products product = new Products();

        product.setProductId(rs.getInt("product_id"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setProductName(rs.getString("product_name"));
        product.setBrand(rs.getString("brand"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setImageUrl(rs.getString("image_url"));
        product.setIsActive(rs.getInt("is_active"));
        product.setCreatedAt(rs.getTimestamp("created_at"));

        return product;
    }

    @Override
    public boolean addProduct(Products product) {

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(ADD_PRODUCT_SQL);

            pstmt.setInt(1, product.getCategoryId());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getBrand());
            pstmt.setString(4, product.getDescription());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setString(6, product.getImageUrl());
            pstmt.setInt(7, product.getIsActive());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Products getProductById(int productId) {

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_PRODUCT_BY_ID_SQL);

            pstmt.setInt(1, productId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractProductFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Products> getAllProducts() {

        List<Products> productList = new ArrayList<>();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_ALL_PRODUCTS_SQL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                productList.add(extractProductFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Products> getProductsByCategory(int categoryId) {

        List<Products> productList = new ArrayList<>();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_SQL);

            pstmt.setInt(1, categoryId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                productList.add(extractProductFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Products> searchProducts(String keyword) {

        List<Products> productList = new ArrayList<>();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(SEARCH_PRODUCTS_SQL);

            String searchKeyword = "%" + keyword + "%";

            pstmt.setString(1, searchKeyword);
            pstmt.setString(2, searchKeyword);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                productList.add(extractProductFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public List<Products> filterProducts(
            String keyword,
            int categoryId,
            double minPrice,
            double maxPrice,
            String sortBy) {

        List<Products> productList = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder(
                    "SELECT * FROM products WHERE is_active = 1"
            );

            List<Object> params = new ArrayList<>();

            if (keyword != null && !keyword.trim().isEmpty()) {
                sql.append(" AND (product_name LIKE ? OR brand LIKE ?)");
                params.add("%" + keyword + "%");
                params.add("%" + keyword + "%");
            }

            if (categoryId > 0) {
                sql.append(" AND category_id = ?");
                params.add(categoryId);
            }

            if (minPrice > 0) {
                sql.append(" AND price >= ?");
                params.add(minPrice);
            }

            if (maxPrice > 0) {
                sql.append(" AND price <= ?");
                params.add(maxPrice);
            }

            if ("lowToHigh".equals(sortBy)) {
                sql.append(" ORDER BY price ASC");
            } else if ("highToLow".equals(sortBy)) {
                sql.append(" ORDER BY price DESC");
            } else {
                sql.append(" ORDER BY product_id DESC");
            }

            PreparedStatement pstmt =
                    connection.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                productList.add(extractProductFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public boolean updateProduct(Products product) {

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_PRODUCT_SQL);

            pstmt.setInt(1, product.getCategoryId());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getBrand());
            pstmt.setString(4, product.getDescription());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setString(6, product.getImageUrl());
            pstmt.setInt(7, product.getIsActive());
            pstmt.setInt(8, product.getProductId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_PRODUCT_SQL);

            pstmt.setInt(1, productId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}