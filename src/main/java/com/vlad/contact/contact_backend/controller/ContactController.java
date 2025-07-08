package com.vlad.contact.contact_backend.controller;

import com.vlad.contact.contact_backend.service.EmailService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContactController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody ContactRequest request) {
        try {
            emailService.sendContactMessage(
                    request.getName(),
                    request.getPhone(),
                    request.getEmail(),
                    request.getMessage()
            );
            return ResponseEntity.ok("Письмо отправлено!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Ошибка при отправке письма.");
        }
    }

    @Data
    public static class ContactRequest {
        @NotBlank
        private String name;
        @NotBlank @Email
        private String email;
        private String phone;
        @NotBlank private String message;
    }
}
