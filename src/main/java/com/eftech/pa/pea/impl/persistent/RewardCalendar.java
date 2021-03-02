package com.eftech.pa.pea.impl.persistent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Month;

@Entity
@Table(name="reward_calendar")
@Getter
@Setter
@NoArgsConstructor
public class RewardCalendar {

    @Id
    @Column(name="rc_id")
    private String efId;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="cg_id")
    private Category category;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="cc_id")
    private CreditCard creditCard;

    @Enumerated(EnumType.STRING)
    @Column(name="month_of_year")
    private Month month;

    @Column(name="percentage")
    private BigDecimal percentage;
}
