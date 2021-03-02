package com.eftech.pa.pea.impl.webapp;

import com.eftech.pa.pea.api.TransactionExtService;
import com.eftech.pa.pea.documentation.CategoryDocumentationConstants;
import com.eftech.pa.pea.documentation.PaymentOptionDocumentationConstants;
import com.eftech.pa.pea.documentation.TransactionDocumentationConstants;
import com.eftech.pa.pea.documentation.UserDocumentationConstants;
import com.eftech.pa.pea.dto.TransactionDTO;
import com.eftech.pa.pea.exception.ApiBadRequestExceptionBody;
import com.eftech.pa.pea.exception.ApiException;
import com.eftech.pa.pea.exception.ApiNotFoundExceptionBody;
import com.eftech.pa.pea.impl.persistent.*;
import com.eftech.pa.pea.impl.utils.ConversionService;
import com.eftech.pa.pea.impl.utils.DataService;
import com.eftech.pa.pea.utils.Generator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionExtServiceImpl implements TransactionExtService {

    private DataService dataService;
    private ConversionService conversionService;

    @Override
    public List<TransactionDTO> searchTransactions(Criteria criteria) throws ApiException {
        List<Transaction> transactions = dataService.searchTransactionsByCriteria(criteria);
        return conversionService.convertToTransactionDTOs(transactions);
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) throws ApiException {
        validateTransactionDTO(transactionDTO);

        String userId = transactionDTO.getUserId();
        User user = dataService.getUserById(userId);
        if (user == null) {
            throw new ApiNotFoundExceptionBody(UserDocumentationConstants.ERROR_USER_NOT_FOUND).wrap();
        }

        String categoryId = transactionDTO.getCategoryId();
        Category category = dataService.getCategoryById(categoryId);
        if (category == null) {
            throw new ApiNotFoundExceptionBody(CategoryDocumentationConstants.ERROR_CATEGORY_NOT_FOUND).wrap();
        }

        String paymentOptionId = transactionDTO.getPaymentOptionId();
        PaymentOption paymentOption = dataService.getPaymentOptionById(paymentOptionId);
        if (paymentOption == null) {
            throw new ApiNotFoundExceptionBody(PaymentOptionDocumentationConstants.ERROR_PAYMENT_OPTION_NOT_FOUND).wrap();
        }

        String creditCardId = transactionDTO.getCreditCardId();
        CreditCard creditCard = dataService.getCreditCardById(creditCardId);

        Transaction transaction = conversionService.convertToPersistedTransaction(transactionDTO);
        transaction.setEfId(Generator.generateEfId());
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setPaymentOption(paymentOption);
        transaction.setCreditCard(creditCard);
        transaction = dataService.saveTransaction(transaction);

        return conversionService.convertToTransactionDTO(transaction);
    }

    @Override
    public TransactionDTO getTransaction(String transactionId) throws ApiException {
        if (StringUtils.isBlank(transactionId)) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_ID_REQUIRED).wrap();
        }

        Transaction transaction = dataService.getTransactionById(transactionId);
        if (transaction == null) {
            throw new ApiNotFoundExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_NOT_FOUND).wrap();
        }
        return conversionService.convertToTransactionDTO(transaction);
    }

    @Override
    public void deleteTransaction(String transactionId) throws ApiException {
        if (StringUtils.isBlank(transactionId)) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_ID_REQUIRED).wrap();
        }

        Transaction existingTransaction = dataService.getTransactionById(transactionId);
        if (existingTransaction == null) {
            throw new ApiNotFoundExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_NOT_FOUND).wrap();
        }

        dataService.deleteTransaction(existingTransaction);
    }

    /**
     * Validate transaction DTO
     * @param transactionDTO DTO to validate
     * @throws ApiException
     */
    private void validateTransactionDTO(TransactionDTO transactionDTO) throws ApiException  {
        String merchantName = transactionDTO.getMerchantName();
        String userId = transactionDTO.getUserId();
        String categoryId = transactionDTO.getCategoryId();
        String paymentOptionId = transactionDTO.getPaymentOptionId();
        Date date = transactionDTO.getDate();
        BigDecimal amount = transactionDTO.getAmount();
        if (StringUtils.isBlank(merchantName)) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_MERCHANT_NAME_REQUIRED).wrap();
        }
        if (StringUtils.isBlank(userId)) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_USER_ID_REQUIRED).wrap();
        }
        if (StringUtils.isBlank(categoryId)) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_CATEGORY_ID_REQUIRED).wrap();
        }
        if (StringUtils.isBlank(paymentOptionId)) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_PAYMENT_OPTION_ID_REQUIRED).wrap();
        }
        if (date == null) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_DATE_REQUIRED).wrap();
        }
        if (date.compareTo(new Date()) > 0) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_DATE_FUTURE).wrap();
        }
        if (amount == null) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_AMOUNT_REQUIRED).wrap();
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiBadRequestExceptionBody(TransactionDocumentationConstants.ERROR_TRANSACTION_AMOUNT_SHOULD_BE_POSITIVE).wrap();
        }
    }

    /**
     * @param dataService the data service to set
     */
    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * @param conversionService the data service to set
     */
    @Autowired
    public void setDataService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
