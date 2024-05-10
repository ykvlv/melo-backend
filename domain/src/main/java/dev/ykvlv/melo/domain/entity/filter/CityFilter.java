package dev.ykvlv.melo.domain.entity.filter;

import dev.ykvlv.melo.domain.entity.City;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city_filter")
public class CityFilter {

    @Id
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "banned", nullable = false)
    private Boolean banned;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

}
