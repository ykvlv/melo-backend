package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.application.service.ConcertService;
import dev.ykvlv.melo.commons.response.ConcertResponse;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(value = "/api/concert")
public class ConcertController {

    private final ConcertService concertService;

    @Operation(summary = "Получить концерт по id")
    @GetMapping("/{id}")
    public ResponseEntity<ConcertResponse> read(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                concertService.read(id),
                HttpStatus.OK
        );
    }

}
