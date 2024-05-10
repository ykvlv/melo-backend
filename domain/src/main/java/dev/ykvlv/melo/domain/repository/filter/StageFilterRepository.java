package dev.ykvlv.melo.domain.repository.filter;

import dev.ykvlv.melo.domain.entity.filter.StageFilter;
import dev.ykvlv.melo.domain.entity.filter.StageFilterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageFilterRepository extends JpaRepository<StageFilter, StageFilterId> {

}
