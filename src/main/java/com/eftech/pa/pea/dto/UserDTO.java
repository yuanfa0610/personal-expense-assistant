package com.eftech.pa.pea.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String efId;

    private String lastName;

    private String firstName;

    private String dateOfBirth;

    private String email;

    private Set<String> creditCardIds;

    private Set<String> transactionIds;
}
