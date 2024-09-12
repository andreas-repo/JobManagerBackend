package org.printassist.jmbackend.controllers.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email {
    private String to;
    private String from;
    private String subject;
    private String messageBody;
}
