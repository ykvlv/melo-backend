package dev.ykvlv.melo.commons.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CityResponse {

    @NonNull
    private Long id;

    @NonNull
    private String name;
}
