package dev.ykvlv.melo.application.controller;

import dev.ykvlv.melo.application.service.UserService;
import dev.ykvlv.melo.application.service.music.YaMusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/music")
public class MusicApplicationController {

    private final YaMusicService yaMusicService;
    private final UserService userService;

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

}
