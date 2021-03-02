package com.eftech.pa.pea.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Month;

@Getter
@Setter
@NoArgsConstructor
public class RewardCalendarDTO {

    private String efId;

    private String categoryId;

    private String creditCardId;

    private Month month;

    private BigDecimal percentage;
}
