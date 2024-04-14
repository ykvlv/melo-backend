package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.commons.response.ConcertResponse;
import lombok.NonNull;

public interface ConcertService {

    @NonNull
    ConcertResponse read(@NonNull Long id);

}
