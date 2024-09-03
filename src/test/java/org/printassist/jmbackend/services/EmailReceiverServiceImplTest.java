package org.printassist.jmbackend.services;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;

import static org.assertj.core.api.Assertions.assertThat;

class EmailReceiverServiceImplTest {

	@InjectMocks
	private EmailReceiverServiceImpl emailReceiverService;

	@Test
	void receiveUnreadMails_whenMethodIsCalled_thenReturnsAllUnreadMessages() throws MessagingException {
		List<Message> receivedMessages = emailReceiverService.receiveUnreadMails();

		assertThat(receivedMessages).isNotNull();
		assertThat(receivedMessages.get(0).getSubject()).isNotEmpty();
	}

}
