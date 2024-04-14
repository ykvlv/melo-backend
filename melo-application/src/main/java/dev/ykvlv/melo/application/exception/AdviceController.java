package dev.ykvlv.melo.application.exception;

import dev.ykvlv.melo.commons.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(BEWrapper.class)
    ResponseEntity<ErrorResponse> BEWrapperHandler(BEWrapper e) {
        return ResponseEntity
                .status(e.getBusinessException().getHttpStatus())
                .body(new ErrorResponse(e.getMessage()));
    }

}
