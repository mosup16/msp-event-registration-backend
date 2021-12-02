package com.example.mspeventregistration.attendant;

import com.example.mspeventregistration.event.Event;
import com.example.mspeventregistration.event.form.Form;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

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
@TypeDefs({@TypeDef(name = "json", typeClass = JsonStringType.class)})
public class Attendant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String name;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private RegistrationInfo registrationInfo;

    @Email
    @NotEmpty
    private String email;
    private long phone;

    @ManyToOne
    private Event event;
    @Builder.Default
    private UUID code = UUID.randomUUID();
}
