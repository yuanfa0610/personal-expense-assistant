package com.eftech.pa.pea.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {

    private String efId;

    private String merchantName;

    private String userId;

    private String categoryId;

    private String paymentOptionId;

    private String creditCardId;

    private Date date;

    private BigDecimal amount;

    private BigDecimal cashbackEarned;
}
