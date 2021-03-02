package com.eftech.pa.pea.impl.utils;

import com.eftech.pa.pea.dto.*;
import com.eftech.pa.pea.impl.persistent.*;

import java.util.List;

public interface ConversionService {

    /**
     * *******************************
     * Category conversion operations
     * *******************************
     */

    /**
     * Convert categories to category DTOs
     * @param categories categories to convert
     * @return a list of category DTOs
     */
    List<CategoryDTO> convertToCategoryDTOs(List<Category> categories);

    /**
     * Convert category DTO to category
     * @param categoryDTO category DTO to convert
     * @return category
     */
    Category convertToPersistedCategory(CategoryDTO categoryDTO);

    /**
     * Convert category to category DTO
     * @param category category to convert
     * @return category DTO
     */
    CategoryDTO convertToCategoryDTO(Category category);


    /**
     * *******************************
     * Credit Card conversion operations
     * *******************************
     */

    /**
     * Convert credit cards to credit card DTOs
     * @param creditCards credit cards to convert
     * @return a list of credit card DTOs
     */
    List<CreditCardDTO> convertToCreditCardDTOs(List<CreditCard> creditCards);

    /**
     * Convert credit card DTO to credit card
     * @param creditCardDTO credit card DTO to convert
     * @return credit card
     */
    CreditCard convertToPersistedCreditCard(CreditCardDTO creditCardDTO);

    /**
     * Convert credit card to credit card DTO
     * @param creditCard credit card to convert
     * @return credit card DTO
     */
    CreditCardDTO convertToCreditCardDTO(CreditCard creditCard);


    /**
     * *******************************
     * Payment Option conversion operations
     * *******************************
     */

    /**
     * Convert payment options to payment option DTOs
     * @param paymentOptions payment options to convert
     * @return a list of payment option DTOs
     */
    List<PaymentOptionDTO> convertToPaymentOptionDTOs(List<PaymentOption> paymentOptions);

    /**
     * Convert payment option DTO to payment option
     * @param paymentOptionDTO payment option DTO to convert
     * @return payment option
     */
    PaymentOption convertToPersistedPaymentOption(PaymentOptionDTO paymentOptionDTO);

    /**
     * Convert payment option to payment option DTO
     * @param paymentOption payment option to convert
     * @return payment option DTO
     */
    PaymentOptionDTO convertToPaymentOptionDTO(PaymentOption paymentOption);


    /**
     * *******************************
     * Reward Calendar conversion operations
     * *******************************
     */

    /**
     * Convert reward calendars to reward calendar DTOs
     * @param rewardCalendars reward calendars to convert
     * @return a list of reward calendar DTOs
     */
    List<RewardCalendarDTO> convertToRewardCalendarDTOs(List<RewardCalendar> rewardCalendars);

    /**
     * Convert reward calendar DTO to reward calendar
     * @param rewardCalendarDTO reward calendar DTO to convert
     * @return reward calendar
     */
    RewardCalendar convertToPersistedRewardCalendar(RewardCalendarDTO rewardCalendarDTO);

    /**
     * Convert reward calendar to reward calendar DTO
     * @param rewardCalendar reward calendar to convert
     * @return reward calendar DTO
     */
    RewardCalendarDTO convertToRewardCalendarDTO(RewardCalendar rewardCalendar);


    /**
     * *******************************
     * Transaction conversion operations
     * *******************************
     */

    /**
     * Convert transactions to transaction DTOs
     * @param transactions transactions to convert
     * @return a list of transaction DTOs
     */
    List<TransactionDTO> convertToTransactionDTOs(List<Transaction> transactions);

    /**
     * Convert transaction DTO to transaction
     * @param transactionDTO transaction DTO to convert
     * @return transaction
     */
    Transaction convertToPersistedTransaction(TransactionDTO transactionDTO);

    /**
     * Convert transaction to transaction DTO
     * @param transaction reward calendar to convert
     * @return transaction DTO
     */
    TransactionDTO convertToTransactionDTO(Transaction transaction);


    /**
     * *******************************
     * User conversion operations
     * *******************************
     */

    /**
     * Convert users to user DTOs
     * @param users users to convert
     * @return a list of user DTOs
     */
    List<UserDTO> convertToUserDTOs(List<User> users);

    /**
     * Convert user DTO to user
     * @param userDTO user DTO to convert
     * @return user
     */
    User convertToPersistedUser(UserDTO userDTO);

    /**
     * Convert user to user DTO
     * @param user user to convert
     * @return user DTO
     */
    UserDTO convertToUserDTO(User user);
}
