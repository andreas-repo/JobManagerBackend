package org.printassist.jmbackend.services;

import java.io.IOException;
import java.util.Properties;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.printassist.jmbackend.controllers.models.Email;
import org.springframework.stereotype.Service;

import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import jakarta.mail.*;
import jakarta.mail.search.FlagTerm;
import java.util.*;

@Service
public class EmailReceiverServiceImpl {
	private Folder mailFolder;
	private static final String PASSWORD = "gfMe45xhAh9B4Zp";
	private static final String USER = "xerox6655@gmx.at";

	public List<Email> receiveEmailMessages() throws MessagingException, IOException {
		Session session = Session.getDefaultInstance(new Properties());
		Store store = session.getStore("imaps");
		store.connect("imap.gmx.net", 993, USER, PASSWORD);
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_WRITE);

		//Fetch unread mails
		Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

		//Sort messages from recent to latest
		Arrays.sort(messages, (m1, m2) -> {
			try {
				return m2.getSentDate().compareTo(m1.getSentDate());
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		});
		List<Email> receivedMails = new ArrayList<>();

		for (Message message : messages) {
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + Arrays.toString(message.getFrom()));
			System.out.println("Text: " + message.getContent().toString());
			Email email = new Email();
			email.setFrom(Arrays.toString(message.getFrom()));
			email.setSubject(message.getSubject());
			email.setMessageBody(getTextFromMessage(message));
			receivedMails.add(email);
			message.setFlag(Flags.Flag.SEEN, true);
		}

		return receivedMails;
	}

	private String getTextFromMessage(Message message) throws MessagingException, IOException {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		}
		if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			return getTextFromMimeMultipart(mimeMultipart);
		}
		return "";
	}

	private String getTextFromMimeMultipart(
			MimeMultipart mimeMultipart)  throws MessagingException, IOException{
		String result = "";
		for (int i = 0; i < mimeMultipart.getCount(); i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				return result + "\n" + bodyPart.getContent(); // without return, same text appears twice in my tests
			}
			result += this.parseBodyPart(bodyPart);
		}
		return result;
	}

	private String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {
		if (bodyPart.isMimeType("text/html")) {
			return "\n" + bodyPart.getContent().toString();

		}
		if (bodyPart.getContent() instanceof MimeMultipart){
			return getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
		}

		return "";
	}
}
