package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.domain.dto.response.EventResponse;
import lombok.NonNull;

public interface EventService {

    @NonNull
    EventResponse read(@NonNull Long id);

}
