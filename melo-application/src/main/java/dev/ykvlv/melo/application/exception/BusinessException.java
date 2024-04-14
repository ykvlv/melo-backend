package dev.ykvlv.melo.application.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BusinessException {
    CONCERT_NOT_FOUND("Концерт с id '%s' не найден", HttpStatus.NOT_FOUND);

    private final String format;
    private final HttpStatus httpStatus;

}
