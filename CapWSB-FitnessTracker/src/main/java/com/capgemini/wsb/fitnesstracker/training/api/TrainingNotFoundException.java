package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link Training} was not found.
 */
@SuppressWarnings("squid:S110") // Suppress SonarLint warnings for inheritance depth
public class TrainingNotFoundException extends NotFoundException {

    // Private constructor for custom messages
    private TrainingNotFoundException(String message) {
        super(message);
    }

    // Public constructor for missing training by ID
    public TrainingNotFoundException(Long id) {
        this("Training with ID=%s was not found".formatted(id));
    }
}
