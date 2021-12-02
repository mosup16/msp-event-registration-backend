package com.example.mspeventregistration.attendant;

import lombok.Data;

import java.util.List;

@Data
public class RegistrationInfo {
    private List<Field> fields;

}

@Data
class Field {
    private String name;
    private String value;
}
