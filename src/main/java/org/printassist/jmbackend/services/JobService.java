package org.printassist.jmbackend.services;

import org.printassist.jmbackend.repositories.entities.Job;

public interface JobService {
	void addJob(Job job);
	void getJob();
	void updateJob();
	void deleteJob();
}
