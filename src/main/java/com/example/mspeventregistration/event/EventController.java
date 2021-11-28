package com.example.mspeventregistration.event;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class EventController {

    private final EventRepo eventRepo;
    private final LogoStore logoStore;

    @PostMapping("/event")
    @Transactional
    public Event test(@RequestBody EventDto eventData) throws IOException {
        Event e = eventRepo.save(eventData.getEvent());
        e = logoStore.setContent(e,eventData.getLogo().getInputStream());
        return e;
    }

    @PutMapping("/event/{id}")
    public Event updateImage(@RequestParam("logo") MultipartFile logo,@PathVariable("id") long id){
        return eventRepo.findById(id)
                .map(logoStore::unsetContent)
                .map(event -> {
                    try {
                        return logoStore.setContent(event,logo.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .orElseThrow(() -> new RuntimeException("an error happened during processing request"));
    }

}
