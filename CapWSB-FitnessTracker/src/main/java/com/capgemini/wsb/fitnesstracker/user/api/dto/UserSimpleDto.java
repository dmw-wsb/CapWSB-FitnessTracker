package com.capgemini.wsb.fitnesstracker.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSimpleDto {
    private Long id;
    private String firstName;
    private String lastName;
}