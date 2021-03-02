package com.eftech.pa.pea.impl.utils;

import com.eftech.pa.pea.impl.persistent.*;

import java.time.Month;
import java.util.List;

public interface DataService {

    /**
     * ****************************
     * Category data operations
     * ****************************
     */

    /**
     * Get all categories
     * @return all categories
     */
    List<Category> getAllCategories();

    /**
     * Save category to database
     * @param category category to save
     * @return saved category
     */
    Category saveCategory(Category category);

    /**
     * Get category with given id
     * @param categoryId id of the category
     * @return category with given id
     */
    Category getCategoryById(String categoryId);

    /**
     * Get category with given name owned by given user
     * @param userId id of the user
     * @param categoryName name of the category
     * @return category with given name owned by given user
     */
    Category getCategoryByUserIdAndCategoryName(String userId, String categoryName);

    /**
     * Update existing category in database
     * @param category category to update
     * @return updated category
     */
    Category updateCategory(Category category);

    /**
     * Delete category from database
     * @param category category to delete
     */
    void deleteCategory(Category category);


    /**
     * ****************************
     * Credit Card data operations
     * ****************************
     */

    /**
     * Get all credit cards
     * @return all credit cards
     */
    List<CreditCard> getAllCreditCards();

    /**
     * Save credit card to database
     * @param creditCard credit card to save
     * @return saved credit card
     */
    CreditCard saveCreditCard(CreditCard creditCard);

    /**
     * Get credit card with given id
     * @param creditCardId id of the credit card
     * @return credit card with given id
     */
    CreditCard getCreditCardById(String creditCardId);

    /**
     * Get list of credit cards owned by the given user
     * @param userId id of the user who owns the credit cards
     * @return list of credit cards owned by given user
     */
    List<CreditCard> getCreditCardsByUserId(String userId);

    /**
     * Get credit card with given name that owned by given user
     * @param userId id of the user who owns the credit card
     * @param creditCardName name of the credit card
     * @return credit card with given name that owned by given user
     */
    CreditCard getCreditCardByUserIdAndCardName(String userId, String creditCardName);

    /**
     * Update existing credit card in database
     * @param creditCard credit card to update
     * @return updated credit card
     */
    CreditCard updateCreditCard(CreditCard creditCard);

    /**
     * Delete credit card from database
     * @param creditCard credit card to delete
     */
    void deleteCreditCard(CreditCard creditCard);


    /**
     * ****************************
     * Payment Option data operations
     * ****************************
     */

    /**
     * Get all payment options
     * @return all payment options
     */
    List<PaymentOption> getAllPaymentOptions();

    /**
     * Save payment option to database
     * @param paymentOption payment option to save
     * @return saved payment option
     */
    PaymentOption savePaymentOption(PaymentOption paymentOption);

    /**
     * Get payment option with given id
     * @param paymentOptionId id of the payment option
     * @return payment option with given id
     */
    PaymentOption getPaymentOptionById(String paymentOptionId);

    /**
     * Get payment option with given name
     * @param paymentOptionName name of the payment option
     * @return payment option with given name
     */
    PaymentOption getPaymentOptionByName(String paymentOptionName);

    /**
     * Delete payment option from database
     * @param paymentOption payment option to delete
     */
    void deletePaymentOption(PaymentOption paymentOption);


    /**
     * ****************************
     * Reward Calendar data operations
     * ****************************
     */

    /**
     * Get all reward calendars
     * @return all reward calendars
     */
    List<RewardCalendar> getAllRewardCalendars();

    /**
     * Get list of reward calendars that belong to given credit card and related to given category
     * @return list of reward calendars that belong to given credit card and related to given category
     */
    List<RewardCalendar> getRewardCalendarsByCardIdAndCategoryId(String creditCardId, String categoryId);

    /**
     * Get reward calendar with given id
     * @param rewardCalendarId id of the reward calendar
     * @return reward calendar with given id
     */
    RewardCalendar getRewardCalendarById(String rewardCalendarId);

    /**
     * Get reward calendar that associated with the given credit card, category, and month information
     * @param creditCardId credit card id that associated with the reward calendar
     * @param categoryId category id that associated with the reward calendar
     * @param month month that associated with the reward calendar
     * @return the reward calendar that associated with the given credit card, category, and month information
     */
    RewardCalendar getRewardCalendarByCardCategoryAndMonth(String creditCardId, String categoryId, Month month);

    /**
     * Save reward calendar to database
     * @param rewardCalendar reward calendar to save
     * @return saved reward calendar
     */
    RewardCalendar saveRewardCalendar(RewardCalendar rewardCalendar);

    /**
     * Save reward calendars to database
     * @param rewardCalendars reward calendars to save
     * @return saved reward calendars
     */
    List<RewardCalendar> saveRewardCalendars(List<RewardCalendar> rewardCalendars);

    /**
     * Update existing reward calender in database
     * @param rewardCalendar reward calendar to update
     * @return updated reward calendar
     */
    RewardCalendar updateRewardCalender(RewardCalendar rewardCalendar);

    /**
     * Delete reward calendar from database
     * @param rewardCalendar reward calendar to delete
     */
    void deleteRewardCalendar(RewardCalendar rewardCalendar);


    /**
     * ****************************
     * Transaction data operations
     * ****************************
     */
    /**
     * Search transactions by criteria
     * @param criteria the criteria of searching
     * @return list of transactions that satisfy the given criteria
     */
    List<Transaction> searchTransactionsByCriteria(Criteria criteria);

    /**
     * Save transaction to database
     * @param transaction transaction to save
     * @return saved transaction
     */
    Transaction saveTransaction(Transaction transaction);

    /**
     * Get transaction with given id
     * @param transactionId id of the transaction
     * @return transaction with given id
     */
    Transaction getTransactionById(String transactionId);

    /**
     * Delete transaction from database
     * @param transaction transaction to delete
     */
    void deleteTransaction(Transaction transaction);


    /**
     * ****************************
     * User data operations
     * ****************************
     */

    /**
     * Get all users
     * @return all users
     */
    List<User> getAllUsers();

    /**
     * Save user to database
     * @param user user to save
     * @return saved user
     */
    User saveUser(User user);

    /**
     * Get user with given id
     * @param userId id of the user
     * @return user with given id
     */
    User getUserById(String userId);

    /**
     * Update existing user in database
     * @param user user to update
     * @return updated user
     */
    User updateUser(User user);

    /**
     * Delete user from database
     * @param user user to delete
     */
    void deleteUser(User user);
}
