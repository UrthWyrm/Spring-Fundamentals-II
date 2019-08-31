package com.codingdojo.dojosandninjas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.services.DojoService;
import com.codingdojo.dojosandninjas.services.NinjaService;

@Controller
public class DojosAndNinjasController {
	@Autowired
	DojoService dojoService;
	@Autowired
	NinjaService ninjaService;
		
		/////////////////////// Home /////////////////////////////////////////////////////////
		@RequestMapping("/")
		public String index(Model model) {
			model.addAttribute("dojo", dojoService.all());
			return "view/index.jsp";
		}
		//////////////////// New Dojo Constructor ///////////////////////////
		@RequestMapping("/dojo/new")
		public String add(Model model, @ModelAttribute("dojo") Dojo dojo) {
			model.addAttribute("dojo", dojo);
			return "view/addDojo.jsp";
		}
		///////////////////// New Dojo Method /////////////////////////////////
		@PostMapping("/dojo/new")
		public String create(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result, RedirectAttributes errors) {
			if(result.hasErrors()) {
				errors.addFlashAttribute("errors", result.getAllErrors());
				return "redirect:/dojo/new";
			} else {
				dojoService.addDojo(dojo);
				return "redirect:/";
			}
		}
		////////////////////////// Find Dojo Constructor ///////////////////////////
		@RequestMapping("/dojo/{id}")
		public String viewDojo(Model model, @PathVariable("id") Long id) {
			Dojo dojo = dojoService.getDojo(id);
			model.addAttribute("ninjas", dojo.getNinja());
			model.addAttribute("dojo", dojo);
			return "view/showDojo.jsp";
		}
		////////////////////////// New Ninja Constructor ////////////////////////////
		@RequestMapping("ninja/new")
		public String show(Model model) {
			model.addAttribute("ninja", new Ninja());
			model.addAttribute("dojos", dojoService.all());
			return "view/addNinja.jsp";
		}
		/////////////////////////// New Ninja Method ////////////////////////////////
		@PostMapping("ninja/new")
		public String add(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result, RedirectAttributes errors) {
			if(result.hasErrors()) {
				errors.addFlashAttribute("errors", result.getAllErrors());
				return "redirect:/ninjas/new";
			} else {
				ninjaService.addNinja(ninja);
				return "redirect:/";
			}
		

		}

}
