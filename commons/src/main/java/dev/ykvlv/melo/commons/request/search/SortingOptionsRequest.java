package dev.ykvlv.melo.commons.request.search;

import dev.ykvlv.melo.commons.validator.NullOrNotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SortingOptionsRequest {

    @NullOrNotBlank(message = "Имя атрибута сортировки может отсутствовать но не должно быть пустым")
    private String attributeName;

    private Boolean descending;

}
