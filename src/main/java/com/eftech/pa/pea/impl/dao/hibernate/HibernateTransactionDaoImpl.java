package com.eftech.pa.pea.impl.dao.hibernate;

import com.eftech.pa.pea.impl.dao.TransactionDao;
import com.eftech.pa.pea.impl.persistent.Criteria;
import com.eftech.pa.pea.impl.persistent.Transaction;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HibernateTransactionDaoImpl extends HibernateGenericDao<Transaction, String> implements TransactionDao {
    @Override
    public List<Transaction> getTransactionsByCriteria(Criteria criteria) {
        if (criteria == null) {
            return new ArrayList<>();
        }
        startTransaction();
        StringBuilder queryString = new StringBuilder("FROM ").append(Transaction.class.getName())
                .append(" WHERE ta_id <> NULL");
        Map<String, Object> parameters = new HashMap<>();
        if (!StringUtils.isEmpty(criteria.getMerchantName())) {
            queryString.append(" AND merchant_name = (:merchantName)");
            parameters.put("merchantName", criteria.getMerchantName());
        }
        if (!StringUtils.isEmpty(criteria.getUserId())) {
            queryString.append(" AND usr_id = (:userId)");
            parameters.put("userId", criteria.getUserId());
        }
        if (!StringUtils.isEmpty(criteria.getCategoryId())) {
            queryString.append(" AND cg_id = (:categoryId)");
            parameters.put("categoryId", criteria.getCategoryId());
        }
        if (!StringUtils.isEmpty(criteria.getCreditCardId())) {
            queryString.append(" AND po_id = (:paymentOptionId)");
            parameters.put("paymentOptionId", criteria.getPaymentOptionId());
        }
        if (!StringUtils.isEmpty(criteria.getCreditCardId())) {
            queryString.append(" AND cc_id = (:creditCardId)");
            parameters.put("creditCardId", criteria.getCreditCardId());
        }
        if (criteria.getDate() != null) {
            queryString.append(" AND date = (:date)");
        }
        Query query = getSession().createQuery(queryString.toString());
        for (String param : parameters.keySet()) {
            query.setParameter(param, parameters.get(param));
        }
        List<Transaction> transactions = query.getResultList();
        endTransaction();
        return transactions;
    }
}
