package org.printassist.jmbackend.controllers;

import java.util.List;

import org.printassist.jmbackend.controllers.responses.HttpResponse;
import org.printassist.jmbackend.exceptions.JobNotFoundException;
import org.printassist.jmbackend.repositories.entities.Job;
import org.printassist.jmbackend.services.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class JobController {

	private static final String JOB_SUCCESS_TEXT = "Job successful ";
	private final JobService jobService;

	@PostMapping("/createJob")
	public ResponseEntity<HttpResponse> createJob(@RequestBody Job job) {
		try {
			jobService.createJob(job);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(HttpResponse.builder().responseText("Error adding job").build());
		}
		return ResponseEntity.ok(HttpResponse.builder().responseText(JOB_SUCCESS_TEXT + "created!").responseBody(job).build());
	}

	@GetMapping("/getJob/{id}")
	public ResponseEntity<HttpResponse> getJob(@PathVariable long id) {
		Job result;
		try {
			result = jobService.getJob(id);
		} catch (JobNotFoundException e) {
			return ResponseEntity.badRequest().body(HttpResponse.builder().responseText("No job found with ID " + id + ".").build());
		}

		return ResponseEntity.ok(HttpResponse.builder().responseText(JOB_SUCCESS_TEXT + "found!").responseBody(result).build());
	}

	@PutMapping("/updateJob/{id}")
	public ResponseEntity<HttpResponse> updateJob(@PathVariable long id, @RequestBody Job job) {
		Job result = jobService.updateJob(id, job);
		return ResponseEntity.ok(HttpResponse.builder().responseText(JOB_SUCCESS_TEXT + "updated!").responseBody(result).build());
	}

	@DeleteMapping("/deleteJob/{id}")
	public ResponseEntity<HttpResponse> deleteJob(@PathVariable long id) {
		jobService.deleteJob(id);
		return ResponseEntity.ok(HttpResponse.builder().responseText(JOB_SUCCESS_TEXT + "deleted!").build());
	}

	@GetMapping("/getAllJobs")
	public List<Job> getAllJobs() {
		return jobService.getAllJobs();
	}

	@GetMapping("/findJob/{emailAddress}")
	public Job findJobThroughEmailAddress(@PathVariable("emailAddress") String emailAddress) {
		return jobService.findJobByEmailAddress(emailAddress);
	}
}
