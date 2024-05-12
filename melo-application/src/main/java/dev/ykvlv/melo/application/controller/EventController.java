package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.application.service.EventService;
import dev.ykvlv.melo.domain.dto.response.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                eventService.read(id),
                HttpStatus.OK
        );
    }

}
