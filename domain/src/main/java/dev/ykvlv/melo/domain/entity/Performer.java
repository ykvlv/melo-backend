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
@Table(name = "performer")
public class Performer {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "performer_id_seq")
    @SequenceGenerator(sequenceName = "performer_id_seq", name = "performer_id_seq", allocationSize = 1)
    private Long id;

}
