package com.example.mspeventregistration.event.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ConstraintParamsPair{
    private final String name;
    private final Map<String,String> params;
}
