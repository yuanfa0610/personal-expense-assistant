package com.eftech.pa.pea.impl.persistent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @Column(name="cg_id")
    private String efId;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="usr_id")
    private User user;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="category",
            cascade={CascadeType.ALL})
    private Set<RewardCalendar> rewardCalendars;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="category",
            cascade={CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Transaction> transactions;
}
