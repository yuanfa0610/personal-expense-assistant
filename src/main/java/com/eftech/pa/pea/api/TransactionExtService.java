package com.eftech.pa.pea.api;

import com.eftech.pa.pea.dto.TransactionDTO;
import com.eftech.pa.pea.exception.ApiException;
import com.eftech.pa.pea.impl.persistent.Criteria;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface TransactionExtService {

    /**
     * Retrieve all transactions
     * @return a list of transaction DTOs
     */
    @RequestMapping(path = "/search", method = RequestMethod.POST)
    List<TransactionDTO> searchTransactions(@RequestBody Criteria criteria) throws ApiException;

    /**
     * Create a new transaction
     * @param transactionDTO DTO of the transaction to create
     * @return DTO of the created transaction
     * @throws ApiException exception to throw
     */
    @RequestMapping(method = RequestMethod.POST)
    TransactionDTO createTransaction(@RequestBody TransactionDTO transactionDTO) throws ApiException;

    /**
     * Retrieve transaction with specified id
     * @param transactionId id of the target transaction
     * @return DTO of the requested transaction
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{transactionId}", method = RequestMethod.GET)
    TransactionDTO getTransaction(@PathVariable("transactionId") String transactionId) throws ApiException;

    /**
     * Delete transaction with specified id
     * @param transactionId id of the target category
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{transactionId}", method = RequestMethod.DELETE)
    void deleteTransaction(@PathVariable("transactionId") String transactionId) throws ApiException;
}
