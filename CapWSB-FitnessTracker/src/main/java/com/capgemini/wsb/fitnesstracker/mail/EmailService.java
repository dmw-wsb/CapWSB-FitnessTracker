package com.capgemini.wsb.fitnesstracker.mail;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String body) {
        // Logic to send email
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        // Replace the above with actual email sending logic
    }
}
