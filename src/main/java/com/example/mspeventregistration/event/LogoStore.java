package com.example.mspeventregistration.event;

import org.springframework.content.commons.repository.ContentStore;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
public interface LogoStore extends ContentStore<Event, UUID> {
}
