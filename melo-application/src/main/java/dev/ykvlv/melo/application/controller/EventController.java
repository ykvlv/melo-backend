package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.application.service.EventService;
import dev.ykvlv.melo.application.service.UserService;
import dev.ykvlv.melo.commons.request.search.SearchEventsRequest;
import dev.ykvlv.melo.commons.response.EventResponse;
import dev.ykvlv.melo.commons.response.SearchEventsResponse;
import dev.ykvlv.melo.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/event")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                eventService.read(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/search")
    @Transactional(readOnly = true)
    public ResponseEntity<SearchEventsResponse> search(@Validated @RequestBody SearchEventsRequest request,
                                                       Authentication authentication) {
        var user = userService.getByUsername(authentication.getName());

        return new ResponseEntity<>(
                eventService.search(request, user),
                HttpStatus.OK
        );
    }
}
