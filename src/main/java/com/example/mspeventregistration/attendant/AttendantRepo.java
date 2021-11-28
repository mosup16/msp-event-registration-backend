package com.example.mspeventregistration.attendant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
public interface AttendantRepo extends JpaRepository<Attendant,Long> {
}
