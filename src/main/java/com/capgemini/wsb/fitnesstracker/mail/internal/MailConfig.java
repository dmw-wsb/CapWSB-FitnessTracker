
package com.capgemini.wsb.fitnesstracker.mail.internal;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties(MailProperties.class)

public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.wp.pl"); // WP.pl's SMTP host
        mailSender.setPort(465); // Default port for WP.pl with SSL
        mailSender.setUsername("damcio7@wp.pl");
        mailSender.setPassword("JELEDAYXLS29L63U");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        props.put("mail.smtp.ssl.enable", "true"); // For SSL
        props.put("mail.debug", "true");

        return mailSender;
    }


}
