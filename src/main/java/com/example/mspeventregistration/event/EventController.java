package com.example.mspeventregistration.event;

import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.function.Function;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/logo/{id}")
    public Resource getLogoById(@PathVariable("id") UUID id){
        return logoStore.getResource(id);
    }

    @GetMapping("/event/{id}/logo")
    public Resource getLogoByEventId(@PathVariable("id") long id){
        return Option.ofOptional(eventRepo.findById(id))
                .onEmpty(() -> {throw new RuntimeException("event not found");})
                .map(logoStore::getResource)
                .getOrElseThrow(() -> new RuntimeException("logo resource couldn't be retrieved"));
    }

    private Function<Event, Option<? extends Event>> saveLogo(MultipartFile logo) {
        return event -> Try.of(() -> logoStore.setContent(event, logo.getInputStream()))
                .onFailure(Throwable::printStackTrace)
                .toOption();
    }
}
