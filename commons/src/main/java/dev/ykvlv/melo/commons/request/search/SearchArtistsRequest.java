package dev.ykvlv.melo.commons.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.ykvlv.melo.commons.validator.NullOrNotBlank;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchArtistsRequest {

    @NullOrNotBlank(message = "Имя артиста может отсутствовать но не должен быть пустым")
    private String name;

    @Valid
    @NotNull(message = "Параметры постраничного поиска должны быть указаны")
    @JsonProperty("pagingOptions")
    private PagingOptionsRequest pagingOptionsRequest;

}
