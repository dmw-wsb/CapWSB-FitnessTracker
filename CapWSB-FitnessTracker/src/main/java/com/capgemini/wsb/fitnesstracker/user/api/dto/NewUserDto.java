package com.capgemini.wsb.fitnesstracker.user.api.dto;

import java.time.LocalDate;

public record NewUserDto(
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email
) {}
