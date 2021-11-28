package com.example.mspeventregistration.event;

import com.example.mspeventregistration.attendant.Attendant;
import lombok.Builder;
import lombok.Data;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String eventName;
    @Lob
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime registrationEndDate;
    private LocalDateTime registrationStartDate;

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