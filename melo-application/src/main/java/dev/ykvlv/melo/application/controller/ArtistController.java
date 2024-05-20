package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.commons.request.search.SearchEventsRequest;
import dev.ykvlv.melo.commons.response.SearchArtistsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artist")
public class ArtistController {

    @PostMapping("/search")
    public ResponseEntity<SearchArtistsResponse> search(@Validated @RequestBody SearchEventsRequest request,
                                                        Authentication authentication) {
        // TODO TBD
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> switchFavorite(Authentication authentication, @PathVariable String id) {
        // TODO TBD
        return null;
    }

}
