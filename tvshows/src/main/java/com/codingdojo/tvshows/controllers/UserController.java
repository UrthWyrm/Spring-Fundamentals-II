package com.codingdojo.tvshows.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.tvshows.models.Show;
import com.codingdojo.tvshows.models.User;
import com.codingdojo.tvshows.services.ShowService;
import com.codingdojo.tvshows.services.UserService;
import com.codingdojo.tvshows.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ShowService showService;
	@Autowired
	private UserValidator userValidator;

/////////////// Login And Registration //////////////////////////////////////////////
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String logReg(@ModelAttribute("user") User user, Model model) {
		return "view/login.jsp";
	}

	
////////////////////////////Logging In ////////////////////////////////////////////////////////////	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session, @ModelAttribute("user") User user) {
		boolean isAuthenticated = userService.authenticateUser(email, password);
		if (isAuthenticated) {
			User u = userService.findByEmail(email);
			session.setAttribute("userId", u.getId());
			return "redirect:/home";
		} else {
			model.addAttribute("user", new User());
			model.addAttribute("error", "Invalid Credentials. Please try again.");
			return "/view/login.jsp";
		}
	}

///////////////////////// Registration //////////////////////////////////
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "view/login.jsp";
		}
		User u = userService.save(user);
		session.setAttribute("userId", u.getId());
		return "redirect:/home";
		
	}

///////////////////////// New Show Method //////////////////////////////////
	@RequestMapping("/home")
	public String add(Model model, HttpSession session, @ModelAttribute("show") Show show) {
		Long id = (Long) session.getAttribute("userId");
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		List<Show> shows = showService.all();
		List<Show> instate = new ArrayList<Show>();
		List<Show> outofstate = new ArrayList<Show>();
		model.addAttribute("today", new Date());
		for (Show audience : shows) {
			if (audience.getId().equals(user.getId())) {
				instate.add(audience);
			} else {
				outofstate.add(audience);
			}
		}
		model.addAttribute("instate", instate);
		model.addAttribute("outofstate", outofstate);		
		return "view/dashboard.jsp";
	}

////////////////////////////Create A Show ////////////////////////////////////////////////////////////	
	@PostMapping("/show/create")
	public String createShow(@Valid @ModelAttribute("show") Show show, HttpSession session, Model model,
			BindingResult result) {
		if (result.hasErrors()) {

			return "view/dashboard.jsp";
		} else {
			Long id = (Long) session.getAttribute("userId");
			User user = userService.findUserById(id);
			show.setProducer(user);
			List<User> watching = new ArrayList<User>();
			watching.add(user);
			show.setUsersWatching(watching);
			showService.create(show);
			return "redirect:/home";
		}
		}

////////////////////////////View Show Constructor ////////////////////////////////////////////////////////////	
	@RequestMapping("/shows/{id}")
	public String show(@PathVariable("id") Long id, @ModelAttribute("emptyshow") Show emptyshow, Model model, HttpSession session) {
		Long uId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(uId);
		Show show = showService.getShow(id);
		model.addAttribute("show", show);
		model.addAttribute("user", user);
		model.addAttribute("users", show.getUsersWatching());
		return "/view/viewShow.jsp";
	}
////////////////////////////Edit An Show Route ////////////////////////////////////////////////////////////	
	@RequestMapping("/shows/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session,
			@Valid @ModelAttribute("emptyshow") Show emptyshow, BindingResult result) {
		Long uId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(uId);
		model.addAttribute("show", showService.getShow(id));
		model.addAttribute("user", user);
		return "view/addShow.jsp";
	}

////////////////////////////Edit An Show Method ////////////////////////////////////////////////////////////	
	@PostMapping("/shows/{id}/edit")
	public String update(Model model, @PathVariable("id") Long id,
			@Valid @ModelAttribute("emptyshow") Show emptyshow, BindingResult result, HttpSession session) {
		Show show = showService.getShow(id);
		Long uId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(uId);
		model.addAttribute("show", show);
		model.addAttribute("user", user);
		if (result.hasErrors()) {
			return "/view/addShow.jsp";
		} else {
			emptyshow.setProducer(show.getProducer());
			emptyshow.setUsersWatching(show.getUsersWatching());
			emptyshow.setCreatedAt(show.getCreatedAt());
			showService.update(emptyshow);
			return "redirect:/home";
		}
		
	}

////////////////////////////Delete An Event ////////////////////////////////////////////////////////////	
	@RequestMapping("/shows/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		Show show = showService.getShow(id);
		for (User user : show.getUsersWatching()) {
			List<Show> myshows = user.getShowsWatched();
			myshows.remove(show);
			user.setShowsWatched(myshows);
			userService.save(user);
		}
			showService.delete(id);
			return "redirect:/home";
		}
		

/////////////////////////////// Logging Out //////////////////////////////////////////////////////////////
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
}
	}
