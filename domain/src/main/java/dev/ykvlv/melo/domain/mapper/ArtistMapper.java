package dev.ykvlv.melo.domain.mapper;

import dev.ykvlv.melo.commons.response.ArtistResponse;
import dev.ykvlv.melo.domain.entity.Artist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ArtistMapper {

    public abstract ArtistResponse map(Artist entity);
}
