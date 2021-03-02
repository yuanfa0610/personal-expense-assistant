package com.eftech.pa.pea.api;

import com.eftech.pa.pea.dto.PaymentOptionDTO;
import com.eftech.pa.pea.exception.ApiException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface PaymentOptionExtService {

    /**
     * Retrieve all payment options
     * @return a list of payment option DTOs
     */
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    List<PaymentOptionDTO> getPaymentOptions();

    /**
     * Create a new payment option
     * @param paymentOptionDTO DTO of the payment option to create
     * @return DTO of the created payment option
     * @throws ApiException exception to throw
     */
    @RequestMapping(method = RequestMethod.POST)
    PaymentOptionDTO createPaymentOption(@RequestBody PaymentOptionDTO paymentOptionDTO) throws ApiException;

    /**
     * Retrieve payment option with specified id
     * @param paymentOptionId id of the target payment method
     * @return DTO of the requested payment method
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{paymentOptionId}", method = RequestMethod.GET)
    PaymentOptionDTO getPaymentOption(@PathVariable("paymentOptionId") String paymentOptionId) throws ApiException;

    /**
     * Delete payment option with specified id
     * @param paymentOptionId id of the target payment option
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{paymentOptionId}", method = RequestMethod.DELETE)
    void deletePaymentOption(@PathVariable("paymentOptionId") String paymentOptionId) throws ApiException;
}
