package dev.ykvlv.melo.application.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BusinessException {
    EVENT_NOT_FOUND("Событие с id '%s' не найдено", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("Пользователь с именем '%s' не найден", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("Пользователь с именем '%s' уже существует", HttpStatus.CONFLICT);

    private final String format;
    private final HttpStatus httpStatus;

}
