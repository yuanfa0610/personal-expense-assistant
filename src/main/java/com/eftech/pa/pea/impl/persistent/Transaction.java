package com.eftech.pa.pea.impl.persistent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @Column(name="ta_id")
    private String efId;

    @Column(name="merchant_name")
    private String merchantName;

    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="usr_id")
    private User user;

    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="cg_id")
    private Category category;

    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="po_id")
    private PaymentOption paymentOption;

    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="cc_id")
    private CreditCard creditCard;

    @Column(name="date")
    private Date date;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="cashback_earned")
    private BigDecimal cashbackEarned;
}
