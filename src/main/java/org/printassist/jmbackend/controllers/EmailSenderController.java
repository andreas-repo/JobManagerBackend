package org.printassist.jmbackend.controllers;

import lombok.AllArgsConstructor;
import org.printassist.jmbackend.controllers.models.Email;
import org.printassist.jmbackend.services.EmailSenderServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmailSenderController {
    private EmailSenderServiceImpl emailSenderService;

    @PostMapping("/sendEmail")
    public Email sendMail(@RequestBody Email email) {
        return emailSenderService.sendEmail(email);
    }
}
