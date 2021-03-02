package com.eftech.pa.pea.impl.dao.hibernate;

import com.eftech.pa.pea.impl.dao.CategoryDao;
import com.eftech.pa.pea.impl.persistent.Category;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HibernateCategoryDaoImpl extends HibernateGenericDao<Category, String> implements CategoryDao {

    @Override
    public List<Category> getCategoriesByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return new ArrayList<>();
        }
        startTransaction();
        StringBuilder queryString = new StringBuilder("FROM ").append(Category.class.getName())
                .append(" WHERE usr_id = (:userId)");
        Query query = getSession().createQuery(queryString.toString());
        query.setParameter("userId", userId);
        List<Category> categories = query.getResultList();
        endTransaction();
        return categories;
    }

    @Override
    public Category getCategoryByUserIdAndCategoryName(String userId, String categoryName) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(categoryName)) {
            return null;
        }
        startTransaction();
        StringBuilder queryString = new StringBuilder("FROM ").append(Category.class.getName())
                .append(" WHERE usr_id = (:userId)")
                .append(" AND name = (:categoryName)");
        Query query = getSession().createQuery(queryString.toString());
        query.setParameter("userId", userId);
        query.setParameter("categoryName", categoryName);
        List<Category> categories = query.getResultList();
        endTransaction();
        if (categories == null || categories.size() == 0) {
            return null;
        }
        return categories.get(0);
    }
}
