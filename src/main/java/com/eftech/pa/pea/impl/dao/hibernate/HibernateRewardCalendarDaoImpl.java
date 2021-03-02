package com.eftech.pa.pea.impl.dao.hibernate;

import com.eftech.pa.pea.impl.dao.RewardCalendarDao;
import com.eftech.pa.pea.impl.persistent.RewardCalendar;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class HibernateRewardCalendarDaoImpl extends HibernateGenericDao<RewardCalendar, String> implements RewardCalendarDao {

    @Override
    public List<RewardCalendar> getRewardCalendarsByCardIdAndCategoryId(String creditCardId, String categoryId) {
        if (StringUtils.isBlank(creditCardId) || StringUtils.isBlank(categoryId)) {
            return new ArrayList<>();
        }
        startTransaction();
        StringBuilder queryString = new StringBuilder("FROM ").append(RewardCalendar.class.getName())
                .append(" WHERE cc_id = (:creditCardId)")
                .append("AND cg_id = (:categoryId)");
        Query query = getSession().createQuery(queryString.toString());
        query.setParameter("creditCardId", creditCardId);
        query.setParameter("categoryId", categoryId);
        List<RewardCalendar> rewardCalendars = query.getResultList();
        endTransaction();
        return rewardCalendars;
    }

    @Override
    public RewardCalendar getRewardCalendarByCardCategoryAndMonth(String creditCardId, String categoryId, Month month) {
        if (StringUtils.isBlank(creditCardId) || StringUtils.isBlank(categoryId) || month == null) {
            return null;
        }
        startTransaction();
        StringBuilder queryString = new StringBuilder("FROM ").append(RewardCalendar.class.getName())
                .append(" WHERE cc_id = (:creditCardId)")
                .append(" AND cg_id = (:categoryId)")
                .append(" AND month_of_year = (:month)");
        Query query = getSession().createQuery(queryString.toString());
        query.setParameter("creditCardId", creditCardId);
        query.setParameter("categoryId", categoryId);
        query.setParameter("month", month);
        List<RewardCalendar> rewardCalendars = query.getResultList();
        endTransaction();
        if (rewardCalendars == null || rewardCalendars.size() == 0) {
            return null;
        }
        return rewardCalendars.get(0);
    }
}
