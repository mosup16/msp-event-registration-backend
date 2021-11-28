package com.example.mspeventregistration.attendant;

import com.example.mspeventregistration.event.Event;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Data
@Builder
public class Attendant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private long phone;

    @ManyToOne
    private Event event;
    @Builder.Default
    private UUID code = UUID.randomUUID();
}
