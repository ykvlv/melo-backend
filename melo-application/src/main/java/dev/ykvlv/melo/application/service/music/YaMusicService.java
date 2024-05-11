package dev.ykvlv.melo.application.service.music;

import lombok.NonNull;

public interface YaMusicService {

    @NonNull
    String fetchToken(@NonNull String code);

    @NonNull
    String fetchLogin(@NonNull String accessToken);
}
