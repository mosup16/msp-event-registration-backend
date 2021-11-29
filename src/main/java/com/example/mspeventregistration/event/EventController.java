package com.example.mspeventregistration.event;

import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class EventController {

    private final EventRepo eventRepo;
    private final LogoStore logoStore;

    @PostMapping("/event/{id}/logo")
    @Transactional
    public Event addLogo(@RequestParam("logo") MultipartFile logo,@PathVariable("id") long eventId) {
        return Option.ofOptional(eventRepo.findById(eventId))
                .flatMap(saveLogo(logo))
                .getOrElseThrow(() -> new RuntimeException("error while adding the logo"));
    }

    @PutMapping("/event/{id}")
    @Transactional
    public Event updateImage(@RequestParam("logo") MultipartFile logo,@PathVariable("id") long eventId){
        return Option.ofOptional(eventRepo.findById(eventId))
                .onEmpty(() -> {throw new RuntimeException(String.format("event with this id  %d dose not exist",eventId));})
                .map(logoStore::unsetContent)
                .flatMap(saveLogo(logo))
                .getOrElseThrow(() -> new RuntimeException("an error happened updating the logo"));
    }

    private Function<Event, Option<? extends Event>> saveLogo(MultipartFile logo) {
        return event -> Try.of(() -> logoStore.setContent(event, logo.getInputStream()))
                .onFailure(Throwable::printStackTrace)
                .toOption();
    }
}
