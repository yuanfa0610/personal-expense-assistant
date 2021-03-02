package com.eftech.pa.pea.dto;

import com.eftech.pa.pea.impl.persistent.RewardCalendar;
import com.eftech.pa.pea.impl.persistent.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private String efId;

    private String userId;

    private String name;

    private String description;

    private Set<RewardCalendar> rewardCalendars;

    private Set<Transaction> transactions;

}
