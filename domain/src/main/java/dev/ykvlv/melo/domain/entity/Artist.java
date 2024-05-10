package dev.ykvlv.melo.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artist")
public class Artist {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_seq")
    @SequenceGenerator(sequenceName = "artist_id_seq", name = "artist_id_seq", allocationSize = 1)
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;
}
