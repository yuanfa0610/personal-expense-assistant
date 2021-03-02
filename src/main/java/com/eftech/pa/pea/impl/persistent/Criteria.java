package com.eftech.pa.pea.impl.persistent;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Criteria {

    private String merchantName;

    private String userId;

    private String categoryId;

    private String paymentOptionId;

    private String creditCardId;

    private Date date;
}
