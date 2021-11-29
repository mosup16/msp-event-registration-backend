package com.example.mspeventregistration.attendant;

import com.example.mspeventregistration.event.Event;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attendant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Email
    @NotEmpty
    private String email;
    private Long phone;

    @ManyToOne
    private Event event;
    @Builder.Default
    private UUID code = UUID.randomUUID();
}
