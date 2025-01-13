package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.user.api.dto.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface for providing user-related operations.
 */
public interface UserProvider {

    /**
     * Retrieves a user by ID.
     * If the user is not found, {@link Optional#empty()} is returned.
     *
     * @param userId The ID of the user to search for
     * @return An {@link Optional} of {@link UserDto}, empty if the user is not found
     */
    Optional<UserDto> findUserById(Long userId);

    /**
     * Retrieves a user by their email.
     * If no user is found with the given email, {@link Optional#empty()} is returned.
     *
     * @param email The email to search for
     * @return An {@link Optional} of {@link UserDto}, empty if no user matches the email
     */
    Optional<UserDto> findUserByEmail(String email);

    /**
     * Retrieves all registered users.
     *
     * @return A list of all users as {@link UserDto}
     */
    List<UserDto> findAllUsers();

    /**
     * Retrieves all users matching a specific email.
     * This might include users with similar emails if specified by implementation.
     *
     * @param email The email to search against
     * @return A list of {@link UserDto} with emails matching the specified criteria
     */
    List<UserDto> findUsersByEmail(String email);

    /**
     * Retrieves all users who were born before the specified date.
     *
     * @param date The cutoff date for determining user age
     * @return A list of {@link UserDto} born before the specified date
     */
    List<UserDto> findAllUsersOlderThan(LocalDate date);
}
