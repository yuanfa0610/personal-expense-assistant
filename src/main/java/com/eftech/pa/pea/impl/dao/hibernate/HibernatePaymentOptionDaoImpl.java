package com.eftech.pa.pea.impl.dao.hibernate;

import com.eftech.pa.pea.impl.dao.PaymentOptionDao;
import com.eftech.pa.pea.impl.persistent.PaymentOption;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernatePaymentOptionDaoImpl extends HibernateGenericDao<PaymentOption, String> implements PaymentOptionDao {

    @Override
    public PaymentOption getPaymentOptionByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        startTransaction();
        StringBuilder queryString = new StringBuilder("FROM ").append(PaymentOption.class.getName())
                .append(" WHERE name = (:name)");
        Query query = getSession().createQuery(queryString.toString());
        query.setParameter("name", name);
        List<PaymentOption> paymentOptions = query.getResultList();
        endTransaction();
        if (paymentOptions == null || paymentOptions.size() == 0) {
            return null;
        }
        return paymentOptions.get(0);
    }
}
