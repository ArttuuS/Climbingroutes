package hh.sof03.Climbingroutes.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DisciplineRepository extends CrudRepository<Discipline, Long> {

	List<Discipline> findByName(@Param("name")String name);
	
	 Long countByDisciplineid(Discipline discipline);
	 
	 //List<Discipline> findByDisciplineId(@Param("disciplineid")Long disciplineid);
}
