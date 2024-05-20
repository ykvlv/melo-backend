package dev.ykvlv.melo.application.controller;


import dev.ykvlv.melo.commons.response.CitiesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {

    @GetMapping
    public ResponseEntity<CitiesResponse> getAll() {
        // TODO TBD
        return null;
    }
}
