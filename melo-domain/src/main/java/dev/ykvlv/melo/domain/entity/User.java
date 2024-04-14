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
@Table(name = "melo_user")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "melo_user_id_seq")
    @SequenceGenerator(sequenceName = "melo_user_id_seq", name = "melo_user_id_seq", allocationSize = 1)
    private Long id;

}
