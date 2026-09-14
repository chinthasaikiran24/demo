package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final EmailService emailService;

    // Constructor Injection
    public NotificationController(
            EmailService emailService) {

        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> sendNotification(
            @RequestBody EmailRequest request) {

        emailService.sendRegistrationMail(
                request.getEmailId());

        return ResponseEntity.ok(
                "Notification email sent successfully");
    }
}