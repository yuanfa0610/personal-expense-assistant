package com.eftech.pa.pea.impl.dao;

import com.eftech.pa.pea.impl.persistent.CreditCard;

import java.util.List;

public interface CreditCardDao extends GenericDao<CreditCard, String>{

    /**
     * Get all credit cards that belong to given user
     * @param userId ID of the user that has credit cards
     * @return a list of credit cards that the given user has
     */
    List<CreditCard> getCreditCardsByUserId(String userId);

    /**
     * Get the credit card with given name that belongs to given user
     * @param userId ID of the user who owns the card
     * @param creditCardName name of the credit card
     * @return CreditCard
     */
    CreditCard getCreditCardByUserIdAndCardName(String userId, String creditCardName);
}
