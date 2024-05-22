package dev.ykvlv.melo.commons.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class EventResponse {

    @NonNull
    private Long id;
    @NonNull
    private String artistName;
    @NonNull
    private String cityName;
    @NonNull
    private LocalDate date;
    @NonNull
    private LocalDateTime createdAt;
    @NonNull
    private String stageName;
    private String photoUrl;
    private String afishaUrl;
    private String artistPhotoUrl;
}
