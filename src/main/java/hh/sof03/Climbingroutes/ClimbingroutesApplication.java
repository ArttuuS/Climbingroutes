package hh.sof03.Climbingroutes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.Climbingroutes.domain.Discipline;
import hh.sof03.Climbingroutes.domain.DisciplineRepository;
import hh.sof03.Climbingroutes.domain.Route;
import hh.sof03.Climbingroutes.domain.RouteRepository;
import hh.sof03.Climbingroutes.domain.Routesetter;
import hh.sof03.Climbingroutes.domain.RoutesetterRepository;
import hh.sof03.Climbingroutes.domain.User;
import hh.sof03.Climbingroutes.domain.UserRepository;

@SpringBootApplication
public class ClimbingroutesApplication {
	private static final Logger log = LoggerFactory.getLogger(ClimbingroutesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ClimbingroutesApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RouteRepository routerepository, RoutesetterRepository routesetterrepository,
			UserRepository userrepository, DisciplineRepository disciplinerepository) {
		return (args) -> {
			// Log information about saving routes
			log.info("save a couple of routes");

			// Save routesetters
			routesetterrepository.save(new Routesetter("Nalle Hukkataival"));
			routesetterrepository.save(new Routesetter("Adam Ondra"));
			routesetterrepository.save(new Routesetter("Thomas Huber"));
			routesetterrepository.save(new Routesetter("Alexander Huber"));

			// Save diciplines
			disciplinerepository.save(new Discipline("Boulder"));
			disciplinerepository.save(new Discipline("Sport"));
			disciplinerepository.save(new Discipline("Traditional"));
			disciplinerepository.save(new Discipline("Speed"));

			// Save routes
			routerepository.save(new Route("Front sector", "6a", "9.11.2023",
					routesetterrepository.findByName("Nalle Hukkataival").get(0),
					disciplinerepository.findByName("Boulder").get(0)));
			routerepository.save(
					new Route("Back sector", "7b", "9.11.2023", routesetterrepository.findByName("Adam Ondra").get(0),
							disciplinerepository.findByName("Boulder").get(0)));
			routerepository.save(new Route("Outdoor walls", "6b+", "10.10.2023",
					routesetterrepository.findByName("Thomas Huber").get(0),
					disciplinerepository.findByName("Sport").get(0)));

			// Save users
			userrepository.save(new User("pekka", "$2a$10$9K9M.XkMfr291FWM3pBtbu2tidtgAfX0HvGQKZ9QT5Ir4YuGLegIa",
					"pekka.saarenheimo@gmail.com", "USER"));
			userrepository.save(new User("ylläpito", "$2a$10$TCOEbO.xx6wDiXm.yNEe/e0n5JQEAl8mdwIATltKAgM4Fs6T7qena",
					"yllapito.kiipeily@gmail.com", "ADMIN"));

			// Log information about fetching all the routes 
			log.info("fetch all routes");
			for (Route route : routerepository.findAll()) {
				log.info(route.toString());
			}
		};
	}
}