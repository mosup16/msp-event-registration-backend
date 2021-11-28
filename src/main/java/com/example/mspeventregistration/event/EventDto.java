package com.example.mspeventregistration.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
public class EventDto {
    private Event event;
    private MultipartFile logo;
}
