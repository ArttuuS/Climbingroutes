package hh.sof03.Climbingroutes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.Climbingroutes.web.ClimbingLogController;
import hh.sof03.Climbingroutes.web.ClimbingrouteController;
import hh.sof03.Climbingroutes.web.DisciplineController;
import hh.sof03.Climbingroutes.web.RoutesetterController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClimbingroutesApplicationTests {

	@Autowired
	ClimbingrouteController climbingrouteController;

	@Autowired
	RoutesetterController routesetterController;

	@Autowired
	DisciplineController disciplineController;

	@Autowired
	ClimbingLogController climbinglogController;

	@Test
	void contextLoads() {
		assertThat(climbingrouteController).isNotNull();
		assertThat(routesetterController).isNotNull();
		assertThat(disciplineController).isNotNull();
		assertThat(climbinglogController).isNotNull();
	}

}
