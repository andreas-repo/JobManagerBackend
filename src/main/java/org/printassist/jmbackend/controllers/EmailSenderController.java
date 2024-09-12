package org.printassist.jmbackend.controllers;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.printassist.jmbackend.controllers.models.Email;
import org.printassist.jmbackend.services.EmailReceiverServiceImpl;
import org.printassist.jmbackend.services.EmailSenderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class EmailSenderController {
    private final EmailSenderServiceImpl emailSenderService;
    private final EmailReceiverServiceImpl emailReceiverService;

    @PostMapping("/sendEmail")
    public Email sendMail(@RequestBody Email email) {
        return emailSenderService.sendEmail(email);
    }

    @GetMapping("/getMails")
    public List<Email> getMails() {
        try {
            return emailReceiverService.receiveEmailMessages();
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
