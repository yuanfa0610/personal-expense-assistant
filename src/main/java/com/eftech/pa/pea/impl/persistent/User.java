package com.eftech.pa.pea.impl.persistent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name="usr_id")
    private String efId;

    @Column(name="last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    @Column(name="dob")
    private Date dateOfBirth;

    @Column(name="email")
    private String email;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="user",
            cascade={CascadeType.ALL})
    private Set<Category> categories;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="user",
            cascade={CascadeType.ALL})
    private Set<CreditCard> creditCards;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="user",
            cascade={CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Transaction> transactions;
}
