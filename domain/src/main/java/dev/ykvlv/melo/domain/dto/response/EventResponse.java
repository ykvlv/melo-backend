package dev.ykvlv.melo.domain.dto.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class EventResponse {

    @NonNull
    private Long id;

}
