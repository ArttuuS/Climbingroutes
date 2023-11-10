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

import hh.sof03.Climbingroutes.domain.Routesetter;
import hh.sof03.Climbingroutes.domain.RoutesetterRepository;

@Controller
public class RoutesetterController {

	@Autowired
	private RoutesetterRepository routesetterrepository;

	@RequestMapping(value = "/routesetterlist", method = RequestMethod.GET)
	public String listRoutesetters(Model model) {
		model.addAttribute("routesetters", routesetterrepository.findAll());
		return "routesetterlist";
	}

	@RequestMapping(value = "/addroutesetter", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addRoutesetter(Model model) {
		model.addAttribute("routesetter", new Routesetter());
		return "addroutesetter";
	}

	@RequestMapping(value = "/saveroutesetter", method = RequestMethod.POST)
	public String saveRoutesetter(Routesetter routesetter) {
		routesetterrepository.save(routesetter);
		return "redirect:/routesetterlist";
	}

	// RESTful service for routesetterlist
	@RequestMapping(value = "/routesetters", method = RequestMethod.GET)
	public @ResponseBody List<Routesetter> routesetterListRest() {
		return (List<Routesetter>) routesetterrepository.findAll();
	}

	// RESTful service for routesetter by id
	@RequestMapping(value = "/routesetters/{routesetterid}", method = RequestMethod.GET)
	public @ResponseBody Optional<Routesetter> findRoutesetterRest(@PathVariable("id") Long routesetterid) {
		return routesetterrepository.findById(routesetterid);
	}

	// RESTful service for saving a new routesetter
	@RequestMapping(value = "/routesetters", method = RequestMethod.POST)
	public @ResponseBody Routesetter saveRoutesetterRest(@RequestBody Routesetter routesetter) {
		return routesetterrepository.save(routesetter);
	}
}
