package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.commons.response.EventResponse;
import lombok.NonNull;

public interface EventService {

    @NonNull
    EventResponse read(@NonNull Long id);

}
