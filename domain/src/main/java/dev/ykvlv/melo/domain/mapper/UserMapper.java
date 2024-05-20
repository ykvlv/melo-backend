package dev.ykvlv.melo.domain.mapper;

import dev.ykvlv.melo.commons.response.UserResponse;
import dev.ykvlv.melo.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "cityName", source = "city.name")
    public abstract UserResponse map(User entity);

}
