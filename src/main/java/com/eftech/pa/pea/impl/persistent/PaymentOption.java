package com.eftech.pa.pea.impl.persistent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="payment_option")
@Getter
@Setter
@NoArgsConstructor
public class PaymentOption {

    @Id
    @Column(name="po_id")
    private String efId;

    @Column(name="name")
    private String name;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="paymentOption",
            cascade={CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Transaction> transactions;
}
