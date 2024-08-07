package org.printassist.jmbackend.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class ResourceProvider {

	@Value("${mail.smtp.user}")
	private String user;

	@Value("${mail.smtp.password}")
	private String password;

	@Value("${mail.smtp.host}")
	private String host;

	@Value("${mail.smtp.port}")
	private String port;

}
