package hh.sof03.Climbingroutes.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RouteRepository extends CrudRepository<Route, Long> {

	// Method to find route by ID
	Optional<Route> findById(@Param("routeid") Long routeid);

	// Method to count routes by routesetter
	Long countByRoutesetter(Routesetter routesetter);

	// Method to find routes by routesetter
	List<Route> findByRoutesetter(Routesetter routesetter);

	// Mmethod to find routes by grade
	List<Route> findByGrade(@Param("grade") String grade);

	// Method to count routes by discipline
	Long countByDiscipline(Discipline discipline);

	// Method to find routes by discipline
	List<Route> findByDiscipline(Discipline discipline);

}
