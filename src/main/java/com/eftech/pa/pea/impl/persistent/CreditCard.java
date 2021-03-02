package com.eftech.pa.pea.impl.persistent;

import com.eftech.pa.pea.enums.CardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="credit_card")
@Getter
@Setter
@NoArgsConstructor
public class CreditCard {

    @Id
    @Column(name="cc_id")
    private String efId;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="usr_id")
    private User user;

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="card_type")
    private CardType cardType;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="creditCard",
            cascade={CascadeType.ALL})
    private Set<RewardCalendar> rewardCalendars;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="creditCard",
            cascade={CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Transaction> transactions;
}
