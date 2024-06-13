package org.printassist.jmbackend.services;

import org.printassist.jmbackend.repositories.JobRepository;
import org.printassist.jmbackend.repositories.entities.Job;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

	private JobRepository jobService;

	@Transactional
	@Override
	public void addJob(Job job) {
		jobService.save(job);
	}

	@Override
	public void getJob() {

	}

	@Override
	public void updateJob() {

	}

	@Override
	public void deleteJob() {

	}
}
