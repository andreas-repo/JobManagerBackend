package org.printassist.jmbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.printassist.jmbackend.exceptions.JobNotFoundException;
import org.printassist.jmbackend.repositories.CustomJobRepository;
import org.printassist.jmbackend.repositories.JobRepository;
import org.printassist.jmbackend.repositories.entities.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class JobServiceImpl implements JobService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

	private JobRepository jobRepository;

	private CustomJobRepository customJobRepository;

	@Override
	public void createJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public Job getJob(long id) throws JobNotFoundException {
		Optional<Job> result = jobRepository.findById(id);
		if (result.isEmpty()) {
			LOGGER.error("No job found with {} id", id);
			throw new JobNotFoundException("Job with {id} doesnt exists in the database. Please check if id is correct!");
		}

		return result.get();
	}

	@Override
	public Job updateJob(long id, Job job) {
		Optional<Job> result = jobRepository.findById(id);
		if (result.isPresent()) {
			 Job resultJob = result.get();
			 job.setId(resultJob.getId());
			 jobRepository.save(job);
			 return resultJob;
		} else {
			jobRepository.save(job);
			return job;
		}
	}

	@Override
	public void deleteJob(long id) {
		jobRepository.deleteById(id);
	}

	@Override
	public List<Job> getAllJobs() {
		List<Job> result = new ArrayList<>();
		Iterable<Job> jobs = jobRepository.findAll();
		jobs.forEach(result::add);
		return result;
	}

	@Override
	public Job findJobByEmailAddress(String emailAddress) {
		return customJobRepository.findJobThroughEmail(emailAddress);
	}

	@Override
	public void deleteJobByEmailAddress(String emailAddress) {
		customJobRepository.deleteJobThroughEmail(emailAddress);
	}
}
