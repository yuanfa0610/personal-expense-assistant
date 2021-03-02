package com.eftech.pa.pea.api;

import com.eftech.pa.pea.dto.CreditCardDTO;
import com.eftech.pa.pea.exception.ApiException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface CreditCardExtService {

    /**
     * Retrieve all credit cards
     * @return a list of credit card DTOs
     */
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    List<CreditCardDTO> getCreditCards();

    /**
     * Create a new credit card
     * @param creditCardDTO DTO of the credit card to create
     * @return DTO of the created credit card
     * @throws ApiException exception to throw
     */
    @RequestMapping(method = RequestMethod.POST)
    CreditCardDTO createCreditCard(@RequestBody CreditCardDTO creditCardDTO) throws ApiException;

    /**
     * Retrieve credit card with specified id
     * @param creditCardId id of the target credit card
     * @return DTO of the requested credit card
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{creditCardId}", method = RequestMethod.GET)
    CreditCardDTO getCreditCard(@PathVariable("creditCardId") String creditCardId) throws ApiException;

    /**
     * Update an existing credit card
     * @param creditCardId id of the target credit card
     * @param creditCardDTO DTO of the credit card to update
     * @return DTO of the updated credit card
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{creditCardId}", method = RequestMethod.PUT)
    CreditCardDTO updateCreditCard(@PathVariable("creditCardId") String creditCardId, @RequestBody CreditCardDTO creditCardDTO) throws ApiException;

    /**
     * Delete credit card with specified id
     * @param creditCardId id of the target credit card
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{creditCardId}", method = RequestMethod.DELETE)
    void deleteCreditCard(@PathVariable("creditCardId") String creditCardId) throws ApiException;
}
