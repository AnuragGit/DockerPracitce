package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DockerStudeController {

	private IDokerStudentService dockerService;

	// Spring Setter Injection
	@Autowired
	public void setUserService(IDokerStudentService dockerService) {
		this.dockerService = dockerService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getAllStudents() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("list", dockerService.listAllStudents());
		return model;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView deleteStudents(@PathVariable long id) {
		dockerService.deleteStudent(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView studentRegister(@ModelAttribute("user") DockerStudent user) {
		ModelAndView model = new ModelAndView("index");
		if (user != null) {
			dockerService.saveStudent(user);
			model.addObject("warning", "Student Registration Success");

		} else {
			model.addObject("danger", "Something Going Bad");

		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("edit");
		DockerStudent user = dockerService.getStudentById(id);
		model.addObject("user", user);
		return model;
	}

	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("id") long id, @RequestParam("name") String name,
			@RequestParam("surname") String surname, @RequestParam("adress") String adress) {
		DockerStudent dstudent = dockerService.getStudentById(id);
		dstudent.setName(name);
		dstudent.setSurname(surname);
		dstudent.setAdress(adress);
		dockerService.saveStudent(dstudent);
		return new ModelAndView("redirect:/");
	}

}
