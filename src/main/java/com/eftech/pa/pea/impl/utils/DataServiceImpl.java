package com.eftech.pa.pea.impl.utils;

import com.eftech.pa.pea.impl.persistent.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Month;
import java.util.List;

@Component
@Transactional
public class DataServiceImpl implements DataService {

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category saveCategory(Category category) {
        return null;
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return null;
    }

    @Override
    public Category getCategoryByUserIdAndCategoryName(String userId, String categoryName) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(Category category) {

    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return null;
    }

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        return null;
    }

    @Override
    public CreditCard getCreditCardById(String creditCardId) {
        return null;
    }

    @Override
    public List<CreditCard> getCreditCardsByUserId(String userId) {
        return null;
    }

    @Override
    public CreditCard getCreditCardByUserIdAndCardName(String userId, String creditCardName) {
        return null;
    }

    @Override
    public CreditCard updateCreditCard(CreditCard creditCard) {
        return null;
    }

    @Override
    public void deleteCreditCard(CreditCard creditCard) {

    }

    @Override
    public List<PaymentOption> getAllPaymentOptions() {
        return null;
    }

    @Override
    public PaymentOption savePaymentOption(PaymentOption paymentOption) {
        return null;
    }

    @Override
    public PaymentOption getPaymentOptionById(String paymentOptionId) {
        return null;
    }

    @Override
    public PaymentOption getPaymentOptionByName(String paymentOptionName) {
        return null;
    }

    @Override
    public void deletePaymentOption(PaymentOption paymentOption) {

    }

    @Override
    public List<RewardCalendar> getAllRewardCalendars() {
        return null;
    }

    @Override
    public List<RewardCalendar> getRewardCalendarsByCardIdAndCategoryId(String creditCardId, String categoryId) {
        return null;
    }

    @Override
    public RewardCalendar getRewardCalendarById(String rewardCalendarId) {
        return null;
    }

    @Override
    public RewardCalendar getRewardCalendarByCardCategoryAndMonth(String creditCardId, String categoryId, Month month) {
        return null;
    }

    @Override
    public RewardCalendar saveRewardCalendar(RewardCalendar rewardCalendar) {
        return null;
    }

    @Override
    public List<RewardCalendar> saveRewardCalendars(List<RewardCalendar> rewardCalendars) {
        return null;
    }

    @Override
    public RewardCalendar updateRewardCalender(RewardCalendar rewardCalendar) {
        return null;
    }

    @Override
    public void deleteRewardCalendar(RewardCalendar rewardCalendar) {

    }

    @Override
    public List<Transaction> searchTransactionsByCriteria(Criteria criteria) {
        return null;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction getTransactionById(String transactionId) {
        return null;
    }

    @Override
    public void deleteTransaction(Transaction transaction) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User getUserById(String userId) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }
}
