package org.printassist.jmbackend.repositories;

import org.printassist.jmbackend.repositories.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomJobRepository extends JpaRepository<Job, Long> {
	@Query(value = "SELECT * FROM jobs WHERE emailaddress = :emailAddress", nativeQuery = true)
	Job findJobThroughEmail(String emailAddress);

	@Modifying
	@Query(value = "DELETE FROM jobs WHERE emailaddress = :emailAddress", nativeQuery = true)
	void deleteJobThroughEmail(String emailAddress);
}
