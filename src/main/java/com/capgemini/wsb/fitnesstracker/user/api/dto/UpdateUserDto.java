
package com.capgemini.wsb.fitnesstracker.user.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.util.List;

public record UpdateUserDto(@Nullable Long id, String firstName, String lastName,
                            @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                            String email, List<String> roles) {


}
