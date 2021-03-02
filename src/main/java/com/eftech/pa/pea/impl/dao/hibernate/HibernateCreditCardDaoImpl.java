package com.eftech.pa.pea.impl.dao.hibernate;

import com.eftech.pa.pea.impl.dao.CreditCardDao;
import com.eftech.pa.pea.impl.persistent.CreditCard;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HibernateCreditCardDaoImpl extends HibernateGenericDao<CreditCard, String> implements CreditCardDao {

    @Override
    public List<CreditCard> getCreditCardsByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return new ArrayList<>();
        }
        startTransaction();
        StringBuilder queryString = new StringBuilder("FROM ").append(CreditCard.class.getName())
                .append(" WHERE usr_id = (:userId)");
        Query query = getSession().createQuery(queryString.toString());
        query.setParameter("userId", userId);
        List<CreditCard> creditCards = query.getResultList();
        endTransaction();
        return creditCards;
    }

    @Override
    public CreditCard getCreditCardByUserIdAndCardName(String userId, String creditCardName) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(creditCardName)) {
            return null;
        }
        startTransaction();
        StringBuilder queryString = new StringBuilder("FROM ").append(CreditCard.class.getName())
                .append(" WHERE usr_id = (:userId)")
                .append(" AND name = (:creditCardName)");
        Query query = getSession().createQuery(queryString.toString());
        query.setParameter("userId", userId);
        query.setParameter("creditCardName", creditCardName);
        List<CreditCard> creditCards = query.getResultList();
        endTransaction();
        if (creditCards == null || creditCards.size() == 0) {
            return null;
        }
        return creditCards.get(0);
    }

}
