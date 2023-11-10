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

import hh.sof03.Climbingroutes.domain.Discipline;
import hh.sof03.Climbingroutes.domain.DisciplineRepository;

@Controller
public class DisciplineController {

	@Autowired
	private DisciplineRepository disciplinerepository;

	@RequestMapping(value = "/disciplinelist", method = RequestMethod.GET)
	public String listDisciplines(Model model) {
		model.addAttribute("disciplines", disciplinerepository.findAll());
		return "disciplinelist";
	}

	@RequestMapping(value = "/adddiscipline", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addDiscipline(Model model) {
		model.addAttribute("discipline", new Discipline());
		return "adddiscipline";
	}

	@RequestMapping(value = "/savediscipline", method = RequestMethod.POST)
	public String saveDiscipline(Discipline discipline) {
		disciplinerepository.save(discipline);
		return "redirect:/disciplinelist";
	}

	// RESTful service for disciplinelist
	@RequestMapping(value = "/disciplines", method = RequestMethod.GET)
	public @ResponseBody List<Discipline> disciplineListRest() {
		return (List<Discipline>) disciplinerepository.findAll();
	}

	// RESTful service for discipline by id
	@RequestMapping(value = "/disciplines/{disciplineid}", method = RequestMethod.GET)
	public @ResponseBody Optional<Discipline> findDisciplineRest(@PathVariable("disciplineid") Long disciplineid) {
		return disciplinerepository.findById(disciplineid);
	}

	// RESTful service for saving a new discipline
	@RequestMapping(value = "/disciplines", method = RequestMethod.POST)
	public @ResponseBody Discipline saveDisciplineRest(@RequestBody Discipline discipline) {
		return disciplinerepository.save(discipline);
	}

}
