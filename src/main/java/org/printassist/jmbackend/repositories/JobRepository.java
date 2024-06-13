package org.printassist.jmbackend.repositories;

import org.printassist.jmbackend.repositories.entities.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

}
