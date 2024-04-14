package dev.ykvlv.melo.domain.mapper;

import dev.ykvlv.melo.commons.response.ConcertResponse;
import dev.ykvlv.melo.domain.entity.Concert;
import lombok.NonNull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ConcertMapper {

    @NonNull
    public abstract ConcertResponse map(@NonNull Concert entity);
}
