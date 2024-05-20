package dev.ykvlv.melo.commons.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EditUserRequest {

    @NotNull(message = "Идентификатор города должен быть указан")
    private Long cityId;

}
