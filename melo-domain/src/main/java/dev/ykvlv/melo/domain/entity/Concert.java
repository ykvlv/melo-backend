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
@Table(name = "concert")
public class Concert {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "concert_id_seq")
    @SequenceGenerator(sequenceName = "concert_id_seq", name = "concert_id_seq", allocationSize = 1)
    private Long id;

}
