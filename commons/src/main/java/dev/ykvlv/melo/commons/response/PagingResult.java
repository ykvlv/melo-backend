package dev.ykvlv.melo.commons.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PagingResult {

    @NonNull
    private Integer pageNumber;

    @NonNull
    private Integer pageSize;

    @NonNull
    private Boolean morePagesAvailable;

}