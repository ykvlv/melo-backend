package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.commons.response.EventResponse;
import dev.ykvlv.melo.commons.response.ExtendedEventResponse;
import lombok.NonNull;

public interface EventService {

    @NonNull
    ExtendedEventResponse read(@NonNull Long id);

}
