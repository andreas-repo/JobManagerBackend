package org.printassist.jmbackend.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.printassist.jmbackend.controllers.models.Email;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

@Service
public class EmailSenderServiceImpl {

    private static final Properties PROPERTIES = new Properties();
    private static final String USERNAME = "xerox6655@gmx.at";
    private static final String PASSWORD = ""; //TODO set password
    private static final String HOST = "mail.gmx.net";
    private static final String PORT = "587";
    private static final boolean DEBUG = true;
    private static final String CONTENT_TYPE = "text/html";

    static {
        PROPERTIES.put("mail.debug", "true");
        PROPERTIES.put("mail.transport.protocol", "smtp");
        PROPERTIES.put("mail.smtp.host", HOST);
        PROPERTIES.put("mail.smtp.port", PORT);
        PROPERTIES.put("mail.smtp.auth", "true");
        PROPERTIES.put("mail.smtp.starttls.enable", "true");
        PROPERTIES.put("mail.smtp.user", USERNAME);
        PROPERTIES.put("mail.smtp.password", PASSWORD);
        PROPERTIES.put("mail.smtp.isSSL", "false");
        PROPERTIES.setProperty("mail.smtp.ssl.protocols", "TLSv1.1 TLSv1.2");
    }

    public Email sendEmail(Email email) {
        Session session = Session.getInstance(PROPERTIES, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(PROPERTIES.getProperty("mail.smtp.user"),
                        PROPERTIES.getProperty("mail.smtp.password"));
            }
        });
        session.setDebug(DEBUG);

        try {
            // create a message with headers
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(USERNAME));
            InternetAddress addressTo = new InternetAddress(email.getTo());
            msg.setRecipients(Message.RecipientType.TO, String.valueOf(addressTo));
            msg.setSubject(email.getSubject());
            msg.setSentDate(new Date());
            msg.setContent(email.getMessageBody(), CONTENT_TYPE);
            // send the message
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
        }

        return email;
    }
}
