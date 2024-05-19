package dev.ykvlv.melo.domain.mapper;

import dev.ykvlv.melo.commons.response.EventResponse;
import dev.ykvlv.melo.domain.entity.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EventMapper {

    public abstract EventResponse map(Event entity);

}
