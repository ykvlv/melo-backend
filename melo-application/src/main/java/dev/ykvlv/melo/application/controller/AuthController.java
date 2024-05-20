package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.application.service.AuthenticationService;
import dev.ykvlv.melo.application.service.UserService;
import dev.ykvlv.melo.application.service.music.YaMusicService;
import dev.ykvlv.melo.commons.request.auth.AuthRequest;
import dev.ykvlv.melo.commons.response.auth.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final YaMusicService yaMusicService;
    private final UserService userService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@Validated @RequestBody AuthRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@Validated @RequestBody AuthRequest request) {
        return authenticationService.signIn(request);
    }

    @GetMapping("/yandex")
    public ResponseEntity<String> yandex(@RequestParam String code, @RequestParam String state) {
        // Получаем токен
        String accessToken = yaMusicService.fetchToken(code);

        // Получаем информацию о пользователе
        String userLogin = yaMusicService.fetchLogin(accessToken);

        // Сохраняем логин пользователя в БД
        userService.changeYaMusicCredentials(state, userLogin);

        return ResponseEntity.ok("Подключено: " + userLogin);
    }

    @GetMapping("/vk")
    public ResponseEntity<String> vk() {
        // TODO TBD
        return null;
    }

}