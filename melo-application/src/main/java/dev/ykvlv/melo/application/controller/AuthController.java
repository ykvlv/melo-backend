package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.application.parser.YaMusicParserImpl;
import dev.ykvlv.melo.application.service.AuthenticationService;
import dev.ykvlv.melo.application.service.UserService;
import dev.ykvlv.melo.application.service.music.YaMusicService;
import dev.ykvlv.melo.commons.request.auth.AuthRequest;
import dev.ykvlv.melo.commons.response.auth.JwtAuthenticationResponse;
import dev.ykvlv.melo.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Контроллер авторизации")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final YaMusicParserImpl yaMusicParser;
    private final YaMusicService yaMusicService;
    private final UserService userService;

    @Operation(summary = "Подключение Яндекс Музыки")
    @GetMapping("/yandex")
    public ResponseEntity<String> yandex(@RequestParam String code, @RequestParam String state) {
        // Получаем токен
        String accessToken = yaMusicService.fetchToken(code);

        // Получаем информацию о пользователе
        String userLogin = yaMusicService.fetchLogin(accessToken);

        // Сохраняем логин пользователя в БД
        User user = userService.changeYaMusicCredentials(state, userLogin);

        yaMusicParser.parseUser(user);

        return ResponseEntity.ok("Подключено: " + userLogin);
    }

    @Operation(summary = "Регистрация")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@Validated @RequestBody AuthRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Получение токена")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@Validated @RequestBody AuthRequest request) {
        return authenticationService.signIn(request);
    }

    @Operation(summary = "Подключение VK Music")
    @GetMapping("/vk")
    public ResponseEntity<String> vk() {
        // TODO TBD
        return null;
    }

}