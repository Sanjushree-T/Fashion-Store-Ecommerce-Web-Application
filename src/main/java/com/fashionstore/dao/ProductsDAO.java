package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.Products;

public interface ProductsDAO {

    boolean addProduct(Products product);

    Products getProductById(int productId);

    List<Products> getAllProducts();

    List<Products> getProductsByCategory(int categoryId);

    List<Products> searchProducts(String keyword);

    List<Products> filterProducts(
            String keyword,
            int categoryId,
            double minPrice,
            double maxPrice,
            String sortBy
    );

    boolean updateProduct(Products product);

    boolean deleteProduct(int productId);
}