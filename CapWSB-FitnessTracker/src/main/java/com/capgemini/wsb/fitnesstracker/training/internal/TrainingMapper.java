package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.dto.TrainingDto;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getUser().getId(),        // User ID
                training.getStartTime(),           // Start time
                training.getEndTime(),             // End time
                training.getActivityType(),        // Activity type
                training.getDistance(),            // Distance
                training.getAverageSpeed()         // Average speed
        );
    }

    public Training toEntity(TrainingDto trainingDto, com.capgemini.wsb.fitnesstracker.user.api.User user) {
        return new Training(
                user,
                trainingDto.getStartTime(),        // Start time
                trainingDto.getEndTime(),          // End time
                trainingDto.getActivityType(),     // Activity type
                trainingDto.getDistance(),         // Distance
                trainingDto.getAverageSpeed()      // Average speed
        );
    }
}
