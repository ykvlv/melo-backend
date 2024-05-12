package dev.ykvlv.melo.domain.dto.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class ErrorResponse {

    @NonNull
    private String message;

    @NonNull
    private LocalDateTime timestamp;

    public ErrorResponse(@NonNull String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

}
