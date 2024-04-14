package dev.ykvlv.melo.domain.repository;

import dev.ykvlv.melo.domain.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {

}
