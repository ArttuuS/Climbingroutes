package hh.sof03.Climbingroutes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.Climbingroutes.domain.Routesetter;
import hh.sof03.Climbingroutes.domain.RoutesetterRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RoutesetterRepositoryTest {

	@Autowired
	private RoutesetterRepository routesetterRepository;

	@Test
	public void findByNameShouldReturnRoutesetter() {
		List<Routesetter> routesetters = routesetterRepository.findByName("Nalle Hukkataival");

		assertThat(routesetters).hasSize(1);
		assertThat(routesetters.get(0).getRoutesetterid()).isEqualTo(1);
	}

	@Test
	public void createNewRoutesetter() {
		Routesetter routesetter = new Routesetter("Pertti Mäkelä");
		routesetterRepository.save(routesetter);
		assertThat(routesetter.getRoutesetterid()).isNotNull();
	}

	@Test
	public void deleteRoutesetter() {
		Routesetter routesetter = new Routesetter("Matti Mäkelä");
		routesetterRepository.save(routesetter);

		Long routesetterId = routesetter.getRoutesetterid();
		routesetterRepository.deleteById(routesetterId);

		Routesetter deletedRoutesetter = routesetterRepository.findById(routesetterId).orElse(null);
		assertThat(deletedRoutesetter).isNull();

	}

}
