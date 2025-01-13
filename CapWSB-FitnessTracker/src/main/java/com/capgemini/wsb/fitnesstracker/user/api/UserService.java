package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.user.api.dto.NewUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.dto.UpdateUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(Long id);

    List<User> findUserByEmail(String email);

    List<User> findUsersOlderThan(LocalDate thresholdDate);

    User createUser(NewUserDto newUserDto);

    User updateUser(Long id, UpdateUserDto updatedUserDto); // Updated to use UpdateUserDto

    void deleteUser(Long id);
}
