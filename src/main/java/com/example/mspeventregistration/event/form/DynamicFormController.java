package com.example.mspeventregistration.event.form;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DynamicFormController {

    @GetMapping("/form/constrains")
    public Map<String, List<Map<String, Object>>> getSupportedConstraints() {
        return Map.of("constraints", SupportedConstraints.supportedConstraints.entrySet()
                .stream()
                .map(constraints -> Map.of("name", constraints.getKey(),
                        "params", constraints.getValue().getParametersNames())
                ).collect(Collectors.toList()));
    }
}
