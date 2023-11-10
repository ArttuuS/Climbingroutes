package hh.sof03.Climbingroutes.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.Climbingroutes.domain.DisciplineRepository;
import hh.sof03.Climbingroutes.domain.Route;
import hh.sof03.Climbingroutes.domain.RouteRepository;
import hh.sof03.Climbingroutes.domain.RoutesetterRepository;

@Controller
public class ClimbingrouteController {

	@Autowired
	private RouteRepository routerepository;

	@Autowired
	private RoutesetterRepository routesetterrepository;
	
	@Autowired DisciplineRepository disciplinerepository;

	// RESTful
	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public @ResponseBody List<Route> routeListRest() {
		return (List<Route>) routerepository.findAll();
	}

	// RESTful for route by id
	@RequestMapping(value = "/routes/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Route> findRouteRest(@PathVariable("id") Long routeId) {
		return routerepository.findById(routeId);
	}

	// RESTful for saving a new route
	@RequestMapping(value = "/routes", method = RequestMethod.POST)
	public @ResponseBody Route saveRouteRest(@RequestBody Route route) {
		return routerepository.save(route);
	}

	@RequestMapping(value = "/climbingroutes", method = RequestMethod.GET)
	public String listRoutes(Model model) {
		model.addAttribute("routes", routerepository.findAll());
		return "routelist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteRoute(@PathVariable("id") Long routeid, Model model) {
		routerepository.deleteById(routeid);
		return "redirect:/climbingroutes";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addRoute(Model model) {
		model.addAttribute("route", new Route());
		model.addAttribute("routesetters", routesetterrepository.findAll());
		model.addAttribute("disciplines", disciplinerepository.findAll());
		return "addroute";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editRoute(@PathVariable("id") Long routeId, Model model) {
		Route route = routerepository.findById(routeId).orElse(null);
		if (route != null) {
			model.addAttribute("route", route);
			model.addAttribute("routesetters", routesetterrepository.findAll());
			model.addAttribute("disciplines", disciplinerepository.findAll());

			return "addroute";
		} else {
			return "redirect:/climbindriutes";
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveRoute(Route route) {
		routerepository.save(route);
		return "redirect:/climbingroutes";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
}
