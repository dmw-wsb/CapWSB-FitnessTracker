package com.capgemini.wsb.fitnesstracker.user.api.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Getter // This annotation generates getter methods for all fields
@AllArgsConstructor
public class UserDto {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthdate;
    private final String email;
}
