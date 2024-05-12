package dev.ykvlv.melo.domain.dto.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserResponse {

    @NonNull
    private String username;

    @NonNull
    private String registeredAt;

    @NonNull
    private String role;

}
