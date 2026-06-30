package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.ProductVariantsDAO;
import com.fashionstore.model.ProductVariants;
import com.fashionstore.util.DBConnection;

public class ProductVariantsDAOImpl implements ProductVariantsDAO {

    private Connection connection;

    // ADD VARIANT
    private static final String ADD_VARIANT_SQL = """
            INSERT INTO product_variants
            (product_id, size, stock_quantity)
            VALUES (?, ?, ?)
            """;

    // GET VARIANT BY ID
    private static final String GET_VARIANT_BY_ID_SQL = """
            SELECT * FROM product_variants
            WHERE variant_id = ?
            """;

    // GET VARIANTS BY PRODUCT ID
    private static final String GET_VARIANTS_BY_PRODUCT_SQL = """
            SELECT * FROM product_variants
            WHERE product_id = ?
            """;

    // UPDATE VARIANT
    private static final String UPDATE_VARIANT_SQL = """
            UPDATE product_variants
            SET product_id = ?,
                size = ?,
                stock_quantity = ?
            WHERE variant_id = ?
            """;

    // DELETE VARIANT
    private static final String DELETE_VARIANT_SQL = """
            DELETE FROM product_variants
            WHERE variant_id = ?
            """;

    // UPDATE STOCK
    private static final String UPDATE_STOCK_SQL = """
            UPDATE product_variants
            SET stock_quantity = ?
            WHERE variant_id = ?
            """;

    public ProductVariantsDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // COMMON METHOD
    private ProductVariants extractVariantFromResultSet(ResultSet rs)
            throws Exception {

        ProductVariants variant = new ProductVariants();

        variant.setVariantId(rs.getInt("variant_id"));
        variant.setProductId(rs.getInt("product_id"));
        variant.setSize(rs.getString("size"));
        variant.setStockQuantity(rs.getInt("stock_quantity"));

        return variant;
    }

    // ADD VARIANT
    @Override
    public boolean addProductVariant(ProductVariants variant) {

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(ADD_VARIANT_SQL);

            pstmt.setInt(1, variant.getProductId());
            pstmt.setString(2, variant.getSize());
            pstmt.setInt(3, variant.getStockQuantity());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // GET VARIANT BY ID
    @Override
    public ProductVariants getVariantById(int variantId) {

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(GET_VARIANT_BY_ID_SQL);

            pstmt.setInt(1, variantId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractVariantFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // GET VARIANTS BY PRODUCT ID
    @Override
    public List<ProductVariants> getVariantsByProductId(int productId) {

        List<ProductVariants> variantsList = new ArrayList<>();

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(
                            GET_VARIANTS_BY_PRODUCT_SQL);

            pstmt.setInt(1, productId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                variantsList.add(
                        extractVariantFromResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return variantsList;
    }

    // UPDATE VARIANT
    @Override
    public boolean updateVariant(ProductVariants variant) {

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_VARIANT_SQL);

            pstmt.setInt(1, variant.getProductId());
            pstmt.setString(2, variant.getSize());
            pstmt.setInt(3, variant.getStockQuantity());
            pstmt.setInt(4, variant.getVariantId());

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE VARIANT
    @Override
    public boolean deleteVariant(int variantId) {

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(DELETE_VARIANT_SQL);

            pstmt.setInt(1, variantId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // UPDATE STOCK
    @Override
    public boolean updateStock(int variantId, int stockQuantity) {

        try {
            PreparedStatement pstmt =
                    connection.prepareStatement(UPDATE_STOCK_SQL);

            pstmt.setInt(1, stockQuantity);
            pstmt.setInt(2, variantId);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
