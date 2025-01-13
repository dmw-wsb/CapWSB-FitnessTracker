package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.mail.EmailService;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import com.capgemini.wsb.fitnesstracker.user.api.dto.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingReportService {

    private final UserService userService;
    private final UserMapper userMapper; // Mapper for User to UserDto mapping
    private final EmailService emailService; // EmailService dependency added

    public void generateAndSendMonthlyReports() {
        // Fetch all users as DTOs
        List<UserDto> users = userMapper.toDtoList(userService.findAllUsers());

        for (UserDto user : users) {
            // Count the trainings for the user
            int trainingCount = 0; // Replace with actual logic to calculate training count

            // Generate the report
            String report = "Great job, " + user.getFirstName() + "!\n" +
                    "You've completed " + trainingCount + " trainings this month.";

            // Send the report via email
            emailService.sendEmail(
                    user.getEmail(),
                    "Your monthly training summary",
                    report
            );
        }
    }
}
