package com.example.mspeventregistration.attendant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface AttendantRepo extends JpaRepository<Attendant,Long> {
}
