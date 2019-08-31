package com.codingdojo.driverslicense.controllers;

import java.util.List;

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

import com.codingdojo.driverslicense.models.DriversLicense;
import com.codingdojo.driverslicense.models.User;
import com.codingdojo.driverslicense.services.DriversLicenseService;
import com.codingdojo.driverslicense.services.UsersService;

@Controller
public class DriversLicenseController {
	@Autowired
		UsersService userService;
		DriversLicenseService licenseService;
		private UsersService usersService;
		private DriversLicenseService driversLicenseService;

		public DriversLicenseController(UsersService usersService, DriversLicenseService driversLicenseService) {
		this.usersService = usersService;
		this.driversLicenseService = driversLicenseService;
	}
	///////////////////////// Home ////////////////////////////////////////////
	@RequestMapping("/")
	public String index(Model model) {
		List<User> user = userService.getAllUsers();
		model.addAttribute("user", user);
		return "view/index.jsp";
	}
	///////////////////////// Adding A User Constructor ////////////////////////////////////////////

	@RequestMapping("/person/new")
	public String showAddUser(@ModelAttribute("user") User user) {
		return "view/addUser.jsp";
	}
	///////////////////////// Adding A User Method ////////////////////////////////////////////

	@PostMapping("/person/new")
	public String AddUser(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes errors) {
		if(result.hasErrors()) {
			errors.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/persons/new";
		} else {
			userService.addUsers(user);
			return "redirect:/";
		}
	}
	///////////////////////// Viewing A License Constructor ////////////////////////////////////////////

	@RequestMapping("/license/new")
	public String showAddDriversLicense(Model model, @ModelAttribute("driversLicense") DriversLicense driversLicense) {
		List<User> user = userService.getAllUsers();
		model.addAttribute("user", user);
		return "view/addLicense.jsp";
	}
	///////////////////////// Adding A License Method ////////////////////////////////////////////

	@PostMapping("/licenses/new")
	public String addDriversLicense(@Valid @ModelAttribute("driversLicense") DriversLicense driversLicense, BindingResult result, RedirectAttributes errors) {
		System.out.println(driversLicense.getExpiration_date());
		if(result.hasErrors()) {
			errors.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/licenses/new";
		} else {
			driversLicenseService.addDriversLicense(driversLicense);
			return "redirect:/";
		}
	}
	///////////////////////// Show User Constructor ////////////////////////////////////////////

	@RequestMapping("/person/{id}")
	public String showOneDriversLicense(Model model, @PathVariable("id") Long id) {
		User user = userService.getUserById(id);
		if(user == null) {
			return "redirect:/";
		}
		model.addAttribute("user", user);
		return "view/viewLicense.jsp";
	}
	///////////////////////// Delete User Constructor ////////////////////////////////////////////

	@RequestMapping("person/{userId}/delete")
	public String deleteUser(@PathVariable("userId") Long userId) {
		userService.deleteUsers(userId);
		return "redirect:/";
	}

}
