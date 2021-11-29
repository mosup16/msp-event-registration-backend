package com.example.mspeventregistration.event;

import com.example.mspeventregistration.attendant.Attendant;
import lombok.*;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String eventName;
    @Lob
    private String description;

    @Builder.Default
    private LocalDateTime startDate = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime endDate = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime registrationEndDate = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime registrationStartDate = LocalDateTime.now();

    @Builder.Default
    private @ContentId UUID logoId = UUID.randomUUID();
    private @ContentLength Long logoLength;
    @Builder.Default
    private String mimetype = "image";


//    @Embedded
//    private Form form;

    @OneToMany(mappedBy = "event")
    private List<Attendant> attendants;
}