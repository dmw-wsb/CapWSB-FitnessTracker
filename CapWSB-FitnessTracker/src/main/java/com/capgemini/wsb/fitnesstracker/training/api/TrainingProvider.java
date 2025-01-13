package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on its ID.
     * If the training with the given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId ID of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);
}
