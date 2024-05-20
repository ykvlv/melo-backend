package dev.ykvlv.melo.domain.entity;

import dev.ykvlv.melo.commons.type.MusicService;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_favorite_artists")
public class UserFavoriteArtists {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_favorite_artists_id_seq")
    @SequenceGenerator(sequenceName = "user_favorite_artists_id_seq", name = "user_favorite_artists_id_seq", allocationSize = 1)
    private Long id;

    @NonNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NonNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;


    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "music_service", nullable = false)
    private MusicService musicService;

}
