package com.eftech.pa.pea.dto;

import com.eftech.pa.pea.enums.CardType;
import com.eftech.pa.pea.impl.persistent.RewardCalendar;
import com.eftech.pa.pea.impl.persistent.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreditCardDTO {

    private String efId;

    private String userId;

    private String name;

    private CardType cardType;

    private Set<RewardCalendar> rewardCalendars;

    private Set<Transaction> transactions;
}
