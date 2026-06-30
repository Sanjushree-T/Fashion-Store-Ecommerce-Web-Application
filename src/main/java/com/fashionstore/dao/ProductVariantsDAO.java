package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.ProductVariants;

public interface ProductVariantsDAO {

    boolean addProductVariant(ProductVariants variant);

    ProductVariants getVariantById(int variantId);

    List<ProductVariants> getVariantsByProductId(int productId);

    boolean updateVariant(ProductVariants variant);

    boolean deleteVariant(int variantId);

    boolean updateStock(int variantId, int stockQuantity);
}
