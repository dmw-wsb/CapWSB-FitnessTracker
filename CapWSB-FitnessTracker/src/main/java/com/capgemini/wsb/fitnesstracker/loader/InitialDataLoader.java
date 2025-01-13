package com.capgemini.wsb.fitnesstracker.loader;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Component
@Profile("loadInitialData")
@Slf4j
public class InitialDataLoader {

    private final JpaRepository<User, Long> userRepository;
    private final JpaRepository<Training, Long> trainingRepository;

    public InitialDataLoader(JpaRepository<User, Long> userRepository, JpaRepository<Training, Long> trainingRepository) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    @EventListener
    @Transactional
    public void loadInitialData(ContextRefreshedEvent event) {
        verifyDependenciesAutowired();

        log.info("Loading initial data into the database...");

        List<User> users = generateSampleUsers();
        userRepository.saveAll(users);

        List<Training> trainings = generateTrainingData(users);
        trainingRepository.saveAll(trainings);

        log.info("Finished loading initial data: {} users and {} trainings added.", users.size(), trainings.size());
    }

    private User generateUser(String name, String lastName, int age) {
        return new User(
                name,
                lastName,
                LocalDate.now().minusYears(age),
                String.format("%s.%s@domain.com", name, lastName).toLowerCase()
        );
    }

    private List<User> generateSampleUsers() {
        return List.of(
                generateUser("Emma", "Johnson", 28),
                generateUser("Ethan", "Taylor", 51),
                generateUser("Olivia", "Davis", 76),
                generateUser("Daniel", "Thomas", 34),
                generateUser("Sophia", "Baker", 49),
                generateUser("Liam", "Jones", 23),
                generateUser("Ava", "Williams", 21),
                generateUser("Noah", "Miller", 39),
                generateUser("Grace", "Anderson", 33),
                generateUser("Oliver", "Swift", 29)
        );
    }

    private List<Training> generateTrainingData(List<User> users) {
        List<Training> trainingData = new ArrayList<>();

        trainingData.add(new Training(users.get(0), parseDate("2024-01-19 08:00:00"), parseDate("2024-01-19 09:30:00"), ActivityType.RUNNING, 10.5, 8.2));
        trainingData.add(new Training(users.get(1), parseDate("2024-01-18 15:30:00"), parseDate("2024-01-18 17:00:00"), ActivityType.CYCLING, 25.0, 18.5));
        trainingData.add(new Training(users.get(2), parseDate("2024-01-17 07:45:00"), parseDate("2024-01-17 09:00:00"), ActivityType.WALKING, 5.2, 5.8));
        trainingData.add(new Training(users.get(3), parseDate("2024-01-16 18:00:00"), parseDate("2024-01-16 19:30:00"), ActivityType.RUNNING, 12.3, 9.0));
        trainingData.add(new Training(users.get(4), parseDate("2024-01-15 12:30:00"), parseDate("2024-01-15 13:45:00"), ActivityType.CYCLING, 18.7, 15.3));
        trainingData.add(new Training(users.get(5), parseDate("2024-01-14 09:00:00"), parseDate("2024-01-14 10:15:00"), ActivityType.WALKING, 3.5, 4.0));
        trainingData.add(new Training(users.get(6), parseDate("2024-01-13 16:45:00"), parseDate("2024-01-13 18:30:00"), ActivityType.RUNNING, 15.0, 10.8));
        trainingData.add(new Training(users.get(7), parseDate("2024-01-12 11:30:00"), parseDate("2024-01-12 12:45:00"), ActivityType.CYCLING, 22.5, 17.2));
        trainingData.add(new Training(users.get(8), parseDate("2024-01-11 07:15:00"), parseDate("2024-01-11 08:30:00"), ActivityType.WALKING, 4.2, 4.5));
        trainingData.add(new Training(users.get(9), parseDate("2024-01-10 14:00:00"), parseDate("2024-01-10 15:15:00"), ActivityType.RUNNING, 11.8, 8.5));

        return trainingData;
    }

    private Date parseDate(String dateTime) {
        return Date.from(
                LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
    }

    private void verifyDependenciesAutowired() {
        if (isNull(userRepository) || isNull(trainingRepository)) {
            throw new IllegalStateException("Dependencies not autowired properly.");
        }
    }
}
