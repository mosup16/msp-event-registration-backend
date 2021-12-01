package com.example.mspeventregistration.attendant;

import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;


@CrossOrigin
public interface AttendantRepo extends JpaRepository<Attendant,Long> {
    Option<Attendant> findAttendantByPhone(Long phone);
    Option<Attendant> findAttendantByName(String name);
    Option<Attendant> findAttendantByCode(UUID code);

}
