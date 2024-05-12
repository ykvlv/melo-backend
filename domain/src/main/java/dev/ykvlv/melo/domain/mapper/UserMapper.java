package dev.ykvlv.melo.domain.mapper;

import dev.ykvlv.melo.domain.dto.response.UserResponse;
import dev.ykvlv.melo.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserResponse map(User entity);

}
