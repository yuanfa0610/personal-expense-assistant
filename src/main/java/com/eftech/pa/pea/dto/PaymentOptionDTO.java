package com.eftech.pa.pea.dto;

import com.eftech.pa.pea.impl.persistent.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PaymentOptionDTO {

    private String efId;

    private String name;

    private Set<Transaction> transactions;
}
