package com.example.mspeventregistration.event;

import com.example.mspeventregistration.attendant.Attendant;
import com.example.mspeventregistration.event.form.Form;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
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
@TypeDefs({@TypeDef(name = "json", typeClass = JsonStringType.class)})
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
    private @ContentId
    UUID logoId = UUID.randomUUID();
    private @ContentLength
    Long logoLength;
    @Builder.Default
    private String mimetype = "image";


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Form form;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Information> additionalInformation;

    @OneToMany(mappedBy = "event")
    private List<Attendant> attendants;
}

@Data
class Information {
    private String name;
    private String value;
}
