package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.commons.request.search.SearchEventsRequest;
import dev.ykvlv.melo.commons.response.SearchArtistsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artist")
@Tag(name = "Контроллер артистов")
public class ArtistController {

    @Operation(summary = "Поиск артистов")
    @PostMapping("/search")
    public ResponseEntity<SearchArtistsResponse> search(@Validated @RequestBody SearchEventsRequest request,
                                                        Authentication authentication) {
        // TODO TBD
        return null;
    }

    @Operation(summary = "Добавление артиста в избранное")
    @PutMapping("/{id}")
    public ResponseEntity<Void> switchFavorite(Authentication authentication, @PathVariable String id) {
        // TODO TBD
        return null;
    }

}
