package com.eftech.pa.pea.impl.dao;

import com.eftech.pa.pea.impl.persistent.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Category, String>{

    /**
     * Get all categories that belong to given user
     * @param userId ID of the user that has categories
     * @return a list of categories that the given user has
     */
    List<Category> getCategoriesByUserId(String userId);

    /**
     * Get the category with given name that belongs to given user
     * @param userId ID of the user who owns the card
     * @param categoryName name of the credit card
     * @return Category
     */
    Category getCategoryByUserIdAndCategoryName(String userId, String categoryName);
}
