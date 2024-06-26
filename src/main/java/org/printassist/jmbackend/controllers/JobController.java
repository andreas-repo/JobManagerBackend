package org.printassist.jmbackend.controllers;

import org.printassist.jmbackend.repositories.entities.Job;
import org.printassist.jmbackend.services.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class JobController {
	private final JobService jobService;

	@PostMapping("/addJob")
	public ResponseEntity addJob(@RequestBody Job job) {
		try {
			jobService.addJob(job);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("Error adding job");
		}
		return ResponseEntity.status(200).body("Job added successfully");
	}

	@GetMapping("/getJob/{id}")
	public ResponseEntity getJob(@PathVariable long id) {
		Job result = jobService.getJob(id);
		if (result == null) {
			return ResponseEntity.badRequest().body("No job found with " + id);
		} else {
			return ResponseEntity.status(200).body(result);
		}
	}
}
