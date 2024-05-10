package dev.ykvlv.melo.domain.repository.filter;

import dev.ykvlv.melo.domain.entity.filter.ArtistFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistFilterRepository extends JpaRepository<ArtistFilter, String> {

}
