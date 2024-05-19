package dev.ykvlv.melo.application.exception;

import dev.ykvlv.melo.commons.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(BEWrapper.class)
    ResponseEntity<ErrorResponse> BEWrapperHandler(BEWrapper e) {
        return ResponseEntity
                .status(e.getBusinessException().getHttpStatus())
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        var result = e.getBindingResult();
        var errors = result.getFieldErrors().stream()
                .map((ee) -> ee.getField() + ": " + ee.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return new ResponseEntity<>(
                new ErrorResponse(errors),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ErrorResponse> badCredentialsExceptionHandler(BadCredentialsException e) {
        return new ResponseEntity<>(
                new ErrorResponse("Неверные логин или пароль"),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();

        return new ResponseEntity<>(
                new ErrorResponse("Внутренняя ошибка сервера, извините за неудобства"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
