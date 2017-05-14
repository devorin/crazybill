package pl.allegro.umk.crazybill.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.allegro.umk.crazybill.domain.Team;

public interface TeamsRepository extends PagingAndSortingRepository<Team, String>  {
    Iterable<Team> findAll(Sort sort);
}
