package org.printassist.jmbackend.exceptions;

public class JobNotFoundException extends Exception {

	public JobNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
