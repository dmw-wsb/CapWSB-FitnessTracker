package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    // Find trainings by user ID
    List<Training> findByUserId(Long userId);

    // Find trainings by activity type
    List<Training> findByActivityType(ActivityType activityType);

    // Find trainings that ended after a specific time
    @Query("SELECT t FROM Training t WHERE t.endTime > :afterTime")
    List<Training> findByEndTimeAfter(@Param("afterTime") Date afterTime);

    // Custom query to count trainings for a user in the current month
    @Query("SELECT COUNT(t) FROM Training t WHERE t.user.id = :userId " +
            "AND FUNCTION('MONTH', t.startTime) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND FUNCTION('YEAR', t.startTime) = FUNCTION('YEAR', CURRENT_DATE)")
    int countTrainingsForUserInCurrentMonth(@Param("userId") Long userId);
}
