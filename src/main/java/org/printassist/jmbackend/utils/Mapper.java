package org.printassist.jmbackend.utils;

import org.printassist.jmbackend.controllers.models.Email;
import org.printassist.jmbackend.repositories.entities.Job;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static List<Job> mapMailsToJobs(List<Email> mailsList) {
        List<Job> jobs = new ArrayList<>();
        mailsList.forEach(mail -> {
            Job job = new Job();
            mail.getMessageBody().lines().forEach(line -> {
                String[] splitStrings = line.split(":");
                String result = splitStrings[1];
                result = result.trim();
                if (splitStrings[0].contains("Vorname")) {
                    job.setFirstName(result);
                } else if (splitStrings[0].contains("Nachname")) {
                    job.setLastName(result);
                } else if (splitStrings[0].contains("Email")) {
                    job.setEmailAddress(result);
                } else if (splitStrings[0].contains("Telefonnummer")) {
                    job.setPhoneNumber(result);
                } else if (splitStrings[0].contains("Druckertyp")) {
                    job.setPrinterType(result);
                }
            });
            jobs.add(job);
        });

        return jobs;
    }
}
