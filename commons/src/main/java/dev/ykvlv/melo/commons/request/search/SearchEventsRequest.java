package dev.ykvlv.melo.commons.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.ykvlv.melo.commons.validator.NullOrNotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SearchEventsRequest {

    private Boolean onlyFavoriteArtists;
    private Boolean anyCity;
    private Boolean onlyActual;

    @NullOrNotBlank(message = "Имя артиста может отсутствовать но не должен быть пустым")
    private String artistName;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    @Valid
    @NotNull(message = "Параметры постраничного поиска должны быть указаны")
	@JsonProperty("pagingOptions")
    private PagingOptionsRequest pagingOptionsRequest;
}
