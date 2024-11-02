package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link User} was not found.
 */
@SuppressWarnings("squid:S110") // This suppresses the Sonar warning about too many parent classes.
public class UserNotFoundException extends NotFoundException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserNotFoundException for a missing user with the specified ID.
     *
     * @param id the ID of the user that was not found.
     */
    public UserNotFoundException(Long id) {
        super("User with ID=" + id + " was not found.");
    }
}
