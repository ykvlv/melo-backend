package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.commons.request.search.SearchEventsRequest;
import dev.ykvlv.melo.commons.response.EventResponse;
import dev.ykvlv.melo.commons.response.SearchEventsResponse;
import dev.ykvlv.melo.domain.entity.User;
import lombok.NonNull;

public interface EventService {

    @NonNull
    EventResponse read(@NonNull Long id);

    @NonNull
    SearchEventsResponse search(@NonNull SearchEventsRequest searchEventsRequest, User user);

}
