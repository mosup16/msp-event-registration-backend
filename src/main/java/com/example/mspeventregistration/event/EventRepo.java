package com.example.mspeventregistration.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(excerptProjection = EventProjection.class)
public interface EventRepo extends JpaRepository<Event,Long> {

}
