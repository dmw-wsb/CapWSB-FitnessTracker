package com.capgemini.wsb.fitnesstracker.user.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter // Optional if setters are needed
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String email;

    // Optional: Add constructors if needed
}
