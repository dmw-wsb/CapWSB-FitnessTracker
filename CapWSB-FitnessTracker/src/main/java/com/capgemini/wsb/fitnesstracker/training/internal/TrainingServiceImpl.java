package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl {

    private final TrainingRepository trainingRepository;

    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    public List<Training> findTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    public List<Training> findFinishedTrainingsAfterTime(String afterTime) {
        try {
            Date afterDate = new SimpleDateFormat("yyyy-MM-dd").parse(afterTime);
            return trainingRepository.findByEndTimeAfter(afterDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + afterTime, e);
        }
    }

    public List<Training> findByActivityType(String activityType) {
        try {
            ActivityType type = ActivityType.valueOf(activityType.toUpperCase());
            return trainingRepository.findByActivityType(type);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid activity type: " + activityType, e);
        }
    }

    public Training createTraining(Training training) {
        if (training.getUser() == null) {
            throw new IllegalArgumentException("Training must have a valid user.");
        }
        return trainingRepository.save(training);
    }

    public Training updateTraining(Long trainingId, Training updatedTraining) {
        return trainingRepository.findById(trainingId)
                .map(existingTraining -> {
                    existingTraining.setStartTime(updatedTraining.getStartTime());
                    existingTraining.setEndTime(updatedTraining.getEndTime());
                    existingTraining.setActivityType(updatedTraining.getActivityType());
                    existingTraining.setDistance(updatedTraining.getDistance());
                    existingTraining.setAverageSpeed(updatedTraining.getAverageSpeed());
                    return trainingRepository.save(existingTraining);
                })
                .orElseThrow(() -> new RuntimeException("Training not found with ID: " + trainingId));
    }
}
