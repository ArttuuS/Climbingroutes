package hh.sof03.Climbingroutes.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.Climbingroutes.domain.ClimbingLog;
import hh.sof03.Climbingroutes.domain.ClimbingLogRepository;
import hh.sof03.Climbingroutes.domain.User;
import hh.sof03.Climbingroutes.domain.UserRepository;

@Controller
public class ClimbingLogController {

	@Autowired
	private ClimbingLogRepository climbingLogRepository;

	@Autowired
	private UserRepository userrepository;

	@RequestMapping("/climbinglog")
	public String showClimbingLog(Model model, Principal principal) {
	    User user = userrepository.findByUsername(principal.getName());
	    List<ClimbingLog> climbingLogs = climbingLogRepository.findByUser(user);
	    model.addAttribute("climbinglogs", climbingLogs); // Updated attribute name
	    return "climbinglog";
	}
	
	// RESTful for climbing logs
	@RequestMapping(value = "/climbinglogs", method = RequestMethod.GET)
	public @ResponseBody List<ClimbingLog> climbingLogListRest(Principal principal) {
	    User user = userrepository.findByUsername(principal.getName());
	    return climbingLogRepository.findByUser(user);
	}

}
