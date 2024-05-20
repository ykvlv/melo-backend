package dev.ykvlv.melo.commons.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class SearchEventsResponse {

    @NonNull
    private PagingResult pagingResult;

    @NonNull
    private List<EventResponse> events;

}
