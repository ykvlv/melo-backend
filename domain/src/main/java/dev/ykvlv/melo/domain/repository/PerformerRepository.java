package dev.ykvlv.melo.domain.repository;

import dev.ykvlv.melo.domain.entity.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {

}
