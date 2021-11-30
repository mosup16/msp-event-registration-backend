package com.example.mspeventregistration.event;

import com.example.mspeventregistration.event.form.Form;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.UUID;

@Projection(name = "event", types = {Event.class})
public interface EventProjection {
    long getId();

    String getEventName();

    String getDescription();

    LocalDateTime getStartDate();

    LocalDateTime getEndDate();

    LocalDateTime getRegistrationEndDate();

    LocalDateTime getRegistrationStartDate();

    UUID getLogoId();

    Long getLogoLength();

    String getMimetype();


    Form form();
}
