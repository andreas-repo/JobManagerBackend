package org.printassist.jmbackend.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.printassist.jmbackend.controllers.models.Email;
import org.printassist.jmbackend.providers.ResourceProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

@Service
public class EmailSenderServiceImpl {
    private final ResourceProvider resourceProvider;
    private static final Properties PROPERTIES = new Properties();
    private static final boolean DEBUG = true;
    private static final String CONTENT_TYPE = "text/html";

	public EmailSenderServiceImpl(ResourceProvider resourceProvider) {
		this.resourceProvider = resourceProvider;
	}

	public Email sendEmail(Email email) {
        PROPERTIES.put("mail.smtp.user", resourceProvider.getUser());
        PROPERTIES.put("mail.smtp.password", resourceProvider.getPassword());
        PROPERTIES.put("mail.smtp.host", resourceProvider.getHost());
        PROPERTIES.put("mail.smtp.port", resourceProvider.getPort());
        PROPERTIES.put("mail.debug", "true");
        PROPERTIES.put("mail.transport.protocol", "smtp");
        PROPERTIES.put("mail.smtp.auth", "true");
        PROPERTIES.put("mail.smtp.starttls.enable", "true");
        PROPERTIES.put("mail.smtp.isSSL", "false");
        PROPERTIES.put("mail.smtp.ssl.protocols", "TLSv1.1 TLSv1.2");

        Session session = Session.getInstance(PROPERTIES, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(resourceProvider.getUser(),
                        resourceProvider.getPassword());
            }
        });

        session.setDebug(DEBUG);

        try {
            // create a message with headers
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(resourceProvider.getUser()));
            InternetAddress addressTo = new InternetAddress(email.getTo());
            msg.setRecipients(Message.RecipientType.TO, String.valueOf(addressTo));
            msg.setSubject(email.getSubject());
            msg.setSentDate(new Date());


            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(email.getMessageBody(), CONTENT_TYPE);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.setHeader("Content-ID", "printassist-logo");
            imagePart.setDisposition(Part.INLINE);
            imagePart.attachFile(Objects.requireNonNull(this.getClass().getResource("/images/printassist-logo.png")).getFile());
            multipart.addBodyPart(imagePart);
            msg.setContent(multipart);

            // send the message
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
			throw new RuntimeException(e);
		}

		return email;
    }
}
