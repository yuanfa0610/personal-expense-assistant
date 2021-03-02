package com.eftech.pa.pea.impl.dao;

import com.eftech.pa.pea.impl.persistent.PaymentOption;

public interface PaymentOptionDao extends GenericDao<PaymentOption, String>{

    /**
     * Get payment option with given name
     * @param name name of the payment option
     * @return PaymentOption
     */
    PaymentOption getPaymentOptionByName(String name);
}
