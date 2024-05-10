package dev.ykvlv.melo.domain.repository.filter;

import dev.ykvlv.melo.domain.entity.filter.CityFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityFilterRepository extends JpaRepository<CityFilter, String> {

}
