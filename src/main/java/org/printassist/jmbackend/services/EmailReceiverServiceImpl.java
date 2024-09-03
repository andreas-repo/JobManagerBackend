package org.printassist.jmbackend.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.search.FlagTerm;

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
			mailFolder.open(Folder.READ_WRITE);

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

	/**
	 * This method retrieves unread messages from inbox and return them as List
	 * @return sorted unread messages as List<Message> object
	 * @throws MessagingException -> throws new RuntimeException
	 */
	public List<Message> receiveUnreadMails() throws MessagingException {
		List<Message> retrievedAndSortedMessages = new ArrayList<>();
		Session session = Session.getDefaultInstance(new Properties());
		//imap is necessary because it supports flags
		Store store = session.getStore("imaps");
		store.connect("imap.gmx.net", 993, USER, PASSWORD);
		Folder inbox = store.getFolder("INBOX");
		//inbox.open(Folder.READ_ONLY);
		inbox.open(Folder.READ_WRITE);

		//Fetch unread messages from inbox folder
		Message[] messages = inbox.search(
			new FlagTerm(new Flags(Flags.Flag.SEEN), false)
		);

		//Sort messages from recent to oldest
		Arrays.sort(messages, (m1, m2) -> {
			try {
				return m2.getSentDate().compareTo(m1.getSentDate());
			} catch (MessagingException e) {
				throw new RuntimeException(e); //TODO create custom exception
			}
		});

		//work through retrieved messages
		for (Message message : messages) {
			System.out.println(
				"sendDate:" + message.getSentDate()
				+
					" subject:" + message.getSubject()
			);
			retrievedAndSortedMessages.add(message);
			//set message to read
			message.setFlag(Flags.Flag.SEEN, true);
		}

		return retrievedAndSortedMessages;
	}
}
