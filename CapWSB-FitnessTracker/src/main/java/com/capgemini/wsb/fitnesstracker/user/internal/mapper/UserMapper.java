package com.capgemini.wsb.fitnesstracker.user.internal.mapper;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.dto.NewUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.dto.UpdateUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.dto.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.dto.UserSimpleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(NewUserDto newUserDto) {
        return new User(
                newUserDto.firstName(),
                newUserDto.lastName(),
                newUserDto.birthdate(),
                newUserDto.email()
        );
    }

    public User toEntity(UpdateUserDto updateUserDto) {
        return new User(
                updateUserDto.getFirstName(),
                updateUserDto.getLastName(),
                updateUserDto.getBirthdate(),
                updateUserDto.getEmail()
        );
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public List<UserSimpleDto> toSimpleDtoList(List<User> users) {
        return users.stream().map(this::toSimpleDto).collect(Collectors.toList());
    }
}
