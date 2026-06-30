package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CategoriesDAO;
import com.fashionstore.model.Categories;
import com.fashionstore.util.DBConnection;

public class CategoriesDAOImpl implements CategoriesDAO {

    private Connection connection;

    // ADD CATEGORY
    private static final String ADD_CATEGORY_SQL = """
            INSERT INTO categories
            (category_name, description)
            VALUES (?, ?)
            """;

    // GET CATEGORY BY ID
    private static final String GET_CATEGORY_BY_ID_SQL = """
            SELECT * FROM categories
            WHERE category_id = ?
            """;

    // GET CATEGORY BY NAME
    private static final String GET_CATEGORY_BY_NAME_SQL = """
            SELECT * FROM categories
            WHERE category_name = ?
            """;

    // UPDATE CATEGORY
    private static final String UPDATE_CATEGORY_SQL = """
            UPDATE categories
            SET category_name = ?,
                description = ?
            WHERE category_id = ?
            """;

    // DELETE CATEGORY
    private static final String DELETE_CATEGORY_SQL = """
            DELETE FROM categories
            WHERE category_id = ?
            """;

    // GET ALL CATEGORIES
    private static final String GET_ALL_CATEGORIES_SQL = """
            SELECT * FROM categories
            """;

    public CategoriesDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // COMMON METHOD
    private Categories extractCategoryFromResultSet(ResultSet rs)
            throws Exception {

        Categories category = new Categories();

        category.setCategoryId(
                rs.getInt("category_id"));

        category.setCategoryName(
                rs.getString("category_name"));

        category.setDescription(
                rs.getString("description"));

        return category;
    }

    // ADD CATEGORY
    @Override
    public boolean addCategory(Categories category) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            ADD_CATEGORY_SQL);

            pstmt.setString(
                    1,
                    category.getCategoryName());

            pstmt.setString(
                    2,
                    category.getDescription());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET CATEGORY BY ID
    @Override
    public Categories getCategoryById(int categoryId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            GET_CATEGORY_BY_ID_SQL);

            pstmt.setInt(1, categoryId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractCategoryFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // GET CATEGORY BY NAME
    @Override
    public Categories getCategoryByName(
            String categoryName) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            GET_CATEGORY_BY_NAME_SQL);

            pstmt.setString(1, categoryName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractCategoryFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE CATEGORY
    @Override
    public boolean updateCategory(
            Categories category) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            UPDATE_CATEGORY_SQL);

            pstmt.setString(
                    1,
                    category.getCategoryName());

            pstmt.setString(
                    2,
                    category.getDescription());

            pstmt.setInt(
                    3,
                    category.getCategoryId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE CATEGORY
    @Override
    public boolean deleteCategory(
            int categoryId) {

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            DELETE_CATEGORY_SQL);

            pstmt.setInt(1, categoryId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET ALL CATEGORIES
    @Override
    public List<Categories> getAllCategories() {

        List<Categories> categoryList =
                new ArrayList<>();

        try {

            PreparedStatement pstmt =
                    connection.prepareStatement(
                            GET_ALL_CATEGORIES_SQL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                categoryList.add(
                        extractCategoryFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryList;
    }
}