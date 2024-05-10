package dev.ykvlv.melo.domain.entity.filter;

import dev.ykvlv.melo.domain.entity.City;
import dev.ykvlv.melo.domain.entity.Stage;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StageFilterId.class)
@Table(name = "stage_filter")
public class StageFilter {

    @Id
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @NonNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @NonNull
    @Column(name = "banned", nullable = false)
    private Boolean banned;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

}
