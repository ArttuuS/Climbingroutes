package hh.sof03.Climbingroutes.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface RouteRepository extends CrudRepository<Route, Long> {

	Optional<Route> findById(@Param("routeid") Long routeid);

}
