package hh.sof03.Climbingroutes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.Climbingroutes.domain.DisciplineRepository;
import hh.sof03.Climbingroutes.domain.Route;
import hh.sof03.Climbingroutes.domain.RouteRepository;
import hh.sof03.Climbingroutes.domain.RoutesetterRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RouteRepositoryTest {

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private RoutesetterRepository routesetterRepository;

	@Autowired
	private DisciplineRepository disciplineRepository;

	@Test
	public void findByGradeShouldReturnRoutes() {

		List<Route> routes = routeRepository.findByGrade("7b");

		assertThat(routes).hasSize(1);
		assertThat(routes.get(0).getRouteid()).isEqualTo(2);
	}

	@Test
	public void createNewRoute() {
		Route route = new Route("Outdoor walls", "8a+", "15.11.2023",
				routesetterRepository.findByName("Adam Ondra").get(0),
				disciplineRepository.findByName("Boulder").get(0));
		routeRepository.save(route);
		assertThat(route.getRouteid()).isNotNull();
	}

	@Test
	public void deleteRoute() {
		Route route = new Route("Front section", "7a+", "12.09.2023",
				routesetterRepository.findByName("Thomas Huber").get(0),
				disciplineRepository.findByName("Sport").get(0));
		routeRepository.save(route);
		Long routeId = route.getRouteid();
		routeRepository.deleteById(routeId);
		Route deletedRoute = routeRepository.findById(routeId).orElse(null);
		assertThat(deletedRoute).isNull();
	
	}
}
