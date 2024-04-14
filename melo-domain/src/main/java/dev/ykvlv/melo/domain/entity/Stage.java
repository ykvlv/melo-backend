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
@Table(name = "stage")
public class Stage {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stage_id_seq")
    @SequenceGenerator(sequenceName = "stage_id_seq", name = "stage_id_seq", allocationSize = 1)
    private Long id;

}
