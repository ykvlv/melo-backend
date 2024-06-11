package dev.ykvlv.melo.domain.mapper;

import dev.ykvlv.melo.commons.response.EventResponse;
import dev.ykvlv.melo.commons.response.ExtendedEventResponse;
import dev.ykvlv.melo.domain.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class EventMapper {

    @Mapping(target = "artistName", source = "artist.name")
    @Mapping(target = "artistPhotoUrl", source = "artist.photoUrl")
    @Mapping(target = "cityName", source = "stage.city.name")
    @Mapping(target = "stageName", source = "stage.name")
    public abstract EventResponse map(Event entity);

    @Mapping(target = "artistName", source = "artist.name")
    @Mapping(target = "artistPhotoUrl", source = "artist.photoUrl")
    @Mapping(target = "cityName", source = "stage.city.name")
    @Mapping(target = "stageName", source = "stage.name")
    @Mapping(target = "latitude", source = "stage.latitude")
    @Mapping(target = "longitude", source = "stage.longitude")
    public abstract ExtendedEventResponse mapFull(Event entity);

}
