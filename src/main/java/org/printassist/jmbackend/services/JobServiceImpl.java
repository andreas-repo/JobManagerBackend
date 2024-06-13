package org.printassist.jmbackend.services;

import java.util.Optional;

import org.printassist.jmbackend.exceptions.JobRepositoryException;
import org.printassist.jmbackend.repositories.JobRepository;
import org.printassist.jmbackend.repositories.entities.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

	private JobRepository jobRepository;

	@Transactional
	@Override
	public void addJob(Job job) {
		jobRepository.save(job);
	}

	@Override
	public Job getJob(long id) {
		Optional<Job> result = jobRepository.findById(id);
		if (!result.isPresent()) {
			LOGGER.error("No job found with {} id", id);
		}
		return result.get();
	}

	@Override
	public void updateJob() {

	}

	@Override
	public void deleteJob() {

	}
}
