package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;

public interface TrainingService {

    List<Training> getAllTrainings();

    List<Training> getAllTrainingsByUserId(Long userId);

    List<Training> getAllFinishedTrainingsAfter(Date afterTime);

    List<Training> getAllTrainingsByActivityType(ActivityType activityType);

    Training saveTraining(Training training);

    Training updateTraining(Long id, Training updatedTraining);
}
