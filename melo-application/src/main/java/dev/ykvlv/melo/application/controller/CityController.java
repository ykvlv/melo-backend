package dev.ykvlv.melo.application.controller;


import dev.ykvlv.melo.commons.response.CitiesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Контроллер городов")
public class CityController {

    @Operation(summary = "Получение списка городов")
    @GetMapping
    public ResponseEntity<CitiesResponse> getAll() {
        // TODO TBD
        return null;
    }
}
