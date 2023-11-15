package hh.sof03.Climbingroutes.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoutesetterRepository extends CrudRepository<Routesetter, Long> {

    List<Routesetter> findByName(@Param("name") String name);

    // Method to count routes by routesetter
    Long countByRoutesetterid(Long routesetterid);

    // Method to find routesetter by id
    List<Route> findByRoutesetterid(Routesetter routesetter);

}
