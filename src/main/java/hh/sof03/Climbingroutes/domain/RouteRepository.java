package hh.sof03.Climbingroutes.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RouteRepository extends CrudRepository<Route, Long> {

    // Existing method to find route by ID
    Optional<Route> findById(@Param("routeid") Long routeid);

    // New method to count routes by routesetter
    Long countByRoutesetter(Routesetter routesetter);

    // New method to find routes by routesetter
    List<Route> findByRoutesetter(Routesetter routesetter);
    
    Long countByDiscipline(Discipline discipline);
    
    List<Route> findByDiscipline(Discipline discipline);
}
