package com.vlad.contact.contact_backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendContactMessage(String name, String phone, String email, String messageText) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setTo("vpodymskiy@icloud.com");
        helper.setSubject("Vladka. Job offer?");
        helper.setText(
                String.format("Имя: %s\nТелефон: %s\nEmail: %s\nСообщение:\n%s", name, phone, email, messageText),
                false
        );

        mailSender.send(message);
    }
}
