package dev.ykvlv.melo.domain.mapper;

import dev.ykvlv.melo.commons.response.EventResponse;
import dev.ykvlv.melo.domain.entity.Event;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class EventMapper {

    @NonNull
    public abstract EventResponse map(@NonNull Event entity);
}
