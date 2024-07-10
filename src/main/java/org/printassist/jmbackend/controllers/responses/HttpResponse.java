package org.printassist.jmbackend.controllers.responses;

import org.printassist.jmbackend.repositories.entities.Job;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class HttpResponse {
	private String responseText;
	private Job responseBody;
}
