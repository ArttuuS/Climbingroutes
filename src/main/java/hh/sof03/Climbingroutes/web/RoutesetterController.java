package hh.sof03.Climbingroutes.web;

import java.util.Collections;
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

import hh.sof03.Climbingroutes.domain.Route;
import hh.sof03.Climbingroutes.domain.RouteRepository;
import hh.sof03.Climbingroutes.domain.Routesetter;
import hh.sof03.Climbingroutes.domain.RoutesetterRepository;

@Controller
public class RoutesetterController {

    @Autowired
    private RoutesetterRepository routesetterrepository;

    @Autowired
    private RouteRepository routeRepository;  // Inject the RouteRepository

    @RequestMapping(value = "/routesetterlist", method = RequestMethod.GET)
    public String listRoutesetters(Model model) {
        Iterable<Routesetter> routesetters = routesetterrepository.findAll();

        // For each routesetter, fetch the routes they have set and count them
        for (Routesetter routesetter : routesetters) {
            List<Route> routes = routeRepository.findByRoutesetter(routesetter);
            Long routeCount = (long) routes.size();
            routesetter.setRouteCount(routeCount);
        }

        model.addAttribute("routesetters", routesetters);
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
    public @ResponseBody Optional<Routesetter> findRoutesetterRest(@PathVariable("routesetterid") Long routesetterid) {
        return routesetterrepository.findById(routesetterid);
    }

    // RESTful service for saving a new routesetter
    @RequestMapping(value = "/routesetters", method = RequestMethod.POST)
    public @ResponseBody Routesetter saveRoutesetterRest(@RequestBody Routesetter routesetter) {
        return routesetterrepository.save(routesetter);
    }

   // // RESTful service for routes by routesetter
   // @RequestMapping(value = "/routes/{routesetterid}", method = RequestMethod.GET)
   // public @ResponseBody List<Route> findRoutesByRoutesetterRest(@PathVariable("routesetterid") Long routesetterid) {
   //     Routesetter routesetter = routesetterrepository.findById(routesetterid).orElse(null);
     //   if (routesetter != null) {
      //      return routeRepository.findByRoutesetter(routesetter);
      //  } else {
      //      // Handle the case where the routesetter is not found
     //       return Collections.emptyList();
     //   }
  //  }
}
