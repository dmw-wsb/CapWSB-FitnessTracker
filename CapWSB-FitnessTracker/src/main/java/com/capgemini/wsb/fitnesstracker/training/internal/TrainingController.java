package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.dto.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final UserRepository userRepository;

    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingService.findAllTrainings();
    }

    @GetMapping("/{userId}")
    public List<Training> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.findTrainingsByUserId(userId);
    }

    @GetMapping("/finished/{afterTime}")
    public List<Training> getFinishedTrainingsAfterTime(@PathVariable String afterTime) {
        return trainingService.findFinishedTrainingsAfterTime(afterTime);
    }

    @GetMapping("/activityType")
    public List<Training> getTrainingByActivityType(@RequestParam("activityType") String activityType) {
        return trainingService.findByActivityType(activityType);
    }

    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody TrainingDto trainingDto) {
        // Fetch the user using the provided userId from the DTO
        User user = userRepository.findById(trainingDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + trainingDto.getUserId()));

        // Create a new Training object and associate the user
        Training training = new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );

        // Save the training
        Training savedTraining = trainingService.createTraining(training);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTraining);
    }

    @PutMapping("/{trainingId}")
    public Training updateTraining(@PathVariable Long trainingId, @RequestBody Training updatedTraining) {
        return trainingService.updateTraining(trainingId, updatedTraining);
    }
}
