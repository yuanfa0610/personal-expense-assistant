package com.eftech.pa.pea.impl.dao;

import com.eftech.pa.pea.impl.persistent.CreditCard;
import com.eftech.pa.pea.impl.persistent.RewardCalendar;
import com.eftech.pa.pea.impl.persistent.User;

import java.time.Month;
import java.util.List;

public interface RewardCalendarDao extends GenericDao<RewardCalendar, String>{

    /**
     * Get all reward calendars that belong to given credit card and related to given category
     * @param creditCardId ID of the credit card
     * @param categoryId ID of the category
     * @return a list of reward calendars that the given credit card has for the given category
     */
    List<RewardCalendar> getRewardCalendarsByCardIdAndCategoryId(String creditCardId, String categoryId);

    /**
     * Get the reward calendar for credit card, category, and month
     * @param creditCardId ID of the credit card that the reward calendar belongs to
     * @param categoryId ID of the category that the reward calendar belongs to
     * @param month month that the reward calendar is in
     * @return RewardCalendar
     */
    RewardCalendar getRewardCalendarByCardCategoryAndMonth(String creditCardId, String categoryId, Month month);
}
