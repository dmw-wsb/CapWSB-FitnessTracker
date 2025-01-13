package com.capgemini.wsb.fitnesstracker.exception.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;

/**
 * Exception indicating that the {@link User} was not found.
 */
@SuppressWarnings("squid:S110") // If SonarLint inheritance depth warning applies
public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }
}

