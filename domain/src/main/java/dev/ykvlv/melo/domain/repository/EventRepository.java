package dev.ykvlv.melo.domain.repository;

import dev.ykvlv.melo.domain.entity.Artist;
import dev.ykvlv.melo.domain.entity.Event;
import dev.ykvlv.melo.domain.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByArtistAndStageAndDate(Artist artist, Stage stage, LocalDate date);

}
