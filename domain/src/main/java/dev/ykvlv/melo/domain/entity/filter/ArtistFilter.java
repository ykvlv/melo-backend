package dev.ykvlv.melo.domain.entity.filter;

import dev.ykvlv.melo.domain.entity.Artist;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artist_filter")
public class ArtistFilter {

    @Id
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "banned", nullable = false)
    private Boolean banned;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

}
