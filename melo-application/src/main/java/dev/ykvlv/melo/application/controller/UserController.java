package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.application.service.UserService;
import dev.ykvlv.melo.commons.request.EditUserRequest;
import dev.ykvlv.melo.commons.response.UserResponse;
import dev.ykvlv.melo.commons.response.music.MusicServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> read(Authentication authentication) {
        return new ResponseEntity<>(
                userService.read(authentication.getName()),
                HttpStatus.OK);
    }

    @GetMapping("/service-info/{service}")
    public ResponseEntity<MusicServiceResponse> yandexInfo(Authentication authentication, @PathVariable String service) {
        // TODO TBD определять какой сервис
        return new ResponseEntity<>(
                userService.getYaMusicInfo(authentication.getName()),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserResponse> update(@Validated @RequestBody EditUserRequest request) {
        // TODO TBD
        return null;
    }

}
