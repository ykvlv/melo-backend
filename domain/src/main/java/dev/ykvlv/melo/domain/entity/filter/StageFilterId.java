package dev.ykvlv.melo.domain.entity.filter;

import dev.ykvlv.melo.domain.entity.City;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StageFilterId implements Serializable {

    @NonNull
    private String name;

    @NonNull
    private City city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageFilterId that = (StageFilterId) o;
        return Objects.equals(getName(), that.getName())
                && Objects.equals(getCity().getId(), that.getCity().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCity().getId());
    }
}
