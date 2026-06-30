package com.fashionstore.dao;

import java.util.List;

import com.fashionstore.model.Categories;

public interface CategoriesDAO {

    boolean addCategory(Categories category);

    Categories getCategoryById(int categoryId);

    Categories getCategoryByName(String categoryName);

    boolean updateCategory(Categories category);

    boolean deleteCategory(int categoryId);

    List<Categories> getAllCategories();
}
