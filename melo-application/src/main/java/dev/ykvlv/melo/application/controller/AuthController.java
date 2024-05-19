package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.application.service.AuthenticationService;
import dev.ykvlv.melo.commons.request.auth.AuthRequest;
import dev.ykvlv.melo.commons.response.auth.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@Validated @RequestBody AuthRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@Validated @RequestBody AuthRequest request) {
        return authenticationService.signIn(request);
    }
}