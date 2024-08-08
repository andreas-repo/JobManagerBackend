package org.printassist.jmbackend.services;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;

@Service
public class EmailReceiverServiceImpl {
	private Folder mailFolder;
	private static final String PASSWORD = "";
	private static final String USER = "xerox6655@gmx.at";

	public void receiveEmailMessages() throws MessagingException {
		Store store = null;
		try {
			Properties properties = new Properties();
			properties.put("mail.pop3.host", "pop.gmail.com");
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");

			Session mailSession = Session.getDefaultInstance(properties);
			//create pop3 store object and connect with it
			store = mailSession.getStore("pop3");
			store.connect("pop.gmail.com", USER, PASSWORD);
			//create folder object and open it
			mailFolder = store.getFolder("INBOX");
			mailFolder.open(Folder.READ_ONLY);

			//retrieve messages from folder in array form and print them
			Message foundMessage = null;
			Message[] messages = mailFolder.getMessages();
			for (final Message msg : messages) {
				final String subject = msg.getSubject();
			}
		} finally {
			if (mailFolder != null) {
				mailFolder.close(false);
			}
			if (store != null) {
				store.close();
			}
		}
	}
}
