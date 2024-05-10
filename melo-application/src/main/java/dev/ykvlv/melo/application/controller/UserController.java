package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.application.service.UserService;
import dev.ykvlv.melo.commons.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                HttpStatus.OK
        );
    }
}
