package org.printassist.jmbackend.services;

import java.util.List;

import org.printassist.jmbackend.exceptions.JobNotFoundException;
import org.printassist.jmbackend.repositories.entities.Job;

public interface JobService {
	void createJob(Job job);
	Job getJob(long id) throws JobNotFoundException;
	Job updateJob(long id, Job job);
	void deleteJob(long id);
	List<Job> getAllJobs();
	Job findJobByEmailAddress(String emailAddress);
}
