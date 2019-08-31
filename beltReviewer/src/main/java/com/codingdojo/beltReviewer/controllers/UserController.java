package com.codingdojo.beltReviewer.controllers;

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

import com.codingdojo.beltReviewer.Validator.UserValidator;
import com.codingdojo.beltReviewer.models.Event;
import com.codingdojo.beltReviewer.models.Message;
import com.codingdojo.beltReviewer.models.User;
import com.codingdojo.beltReviewer.services.EventService;
import com.codingdojo.beltReviewer.services.MessageService;
import com.codingdojo.beltReviewer.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	MessageService messageService;
	@Autowired
	private UserValidator userValidator;
///////////////////////////// Array of US States ////////////////////////////////////////////////////
	private final String[] usStates = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID",
			"IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ",
			"NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV",
			"WY" };

/////////////// Login And Registration //////////////////////////////////////////////
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String logReg(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("usStates", usStates);
		return "view/home.jsp";
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
	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		userValidator.validate(user, result);
		model.addAttribute("usStates", usStates);
		if (result.hasErrors()) {
			return "home.jsp";
		} else {
			userService.save(user);
			return "redirect:/";
		}
	}

////////////// New Event Route //////////////////////////////
	@RequestMapping("/home")
	public String add(Model model, HttpSession session,@Valid @ModelAttribute("event") Event event) {
		Long id = (Long) session.getAttribute("userId");
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		List<Event> events = eventService.all();
		List<Event> instate = new ArrayList<Event>();
		List<Event> outofstate = new ArrayList<Event>();
		model.addAttribute("today", new Date());
		for (Event festival : events) {
			if(festival.getState().equals(user.getState())) {
				instate.add(festival);
			}
			else {
				outofstate.add(festival);
			}
		}
		model.addAttribute("instate", instate);
		model.addAttribute("outofstate", outofstate);
		model.addAttribute("usStates", usStates);
		return "view/events.jsp";
	}

//////////////////////////// Create An Event Route ////////////////////////////////////////////////////////////	
	@PostMapping("/event/create")
	public String createEvent(@Valid @ModelAttribute("event") Event event, HttpSession session, Model model,
			BindingResult result) {
		if (result.hasErrors()) {

			return "view/events.jsp";
		} else {
			Date today = new Date();
			if (event.getDate() == null || event.getDate().before(today)) {
				event.setDate(today);
			}
			Long id = (Long) session.getAttribute("userId");
			User user = userService.findUserById(id);
			event.setHost(user);
			List<User> going = new ArrayList<User>();
			going.add(user);
			event.setUsersAttending(going);
			eventService.create(event);
			return "redirect:/home";
		}
	}

//////////////////////////// Join An Event Route////////////////////////////////////////////////////////////	
	@RequestMapping("/events/{id}/join")
	public String join(@PathVariable("id") Long id, HttpSession session) {
		Long uId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(uId);
		Event event = eventService.getEvent(id);
		List<User> going = event.getUsersAttending();
		going.add(user);
		event.setUsersAttending(going);
		userService.update(user);
		return "redirect:/home";
	}

//////////////////////////// Leave An Event Route ////////////////////////////////////////////////////////////	
	@RequestMapping("/events/{id}/leave")
	public String bail(@PathVariable("id") Long id, HttpSession session) {
		Long uId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(uId);
		Event event = eventService.getEvent(id);
		List<User> going = event.getUsersAttending();
		for (int i = 0; i < going.size(); i++) {
			if (going.get(i).getId() == user.getId()) {
				going.remove(i);
			}
		}
		event.setUsersAttending(going);
		userService.update(user);
		return "redirect:/home";
	}

//////////////////////////// View Event Route ////////////////////////////////////////////////////////////	
	@RequestMapping("/events/{id}")
	public String show(@PathVariable("id") Long id, Model model, HttpSession session,
			@ModelAttribute("msg") Message message) {
		Long uId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(uId);
		Event event = eventService.getEvent(id);
		model.addAttribute("event", event);
		model.addAttribute("user", user);
		model.addAttribute("users", event.getUsersAttending());
		model.addAttribute("messages", event.getMessages());
		return "/view/viewEvent.jsp";
	}

//////////////////////////// Add Message To Event ////////////////////////////////////////////////////////////	
	@PostMapping("/events/{id}/addmsg")
	public String message(@PathVariable("id") Long id, @Valid @ModelAttribute("msg") Message msg, BindingResult result,
			Model model, HttpSession session) {
		Long uId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(uId);
		Event event = eventService.getEvent(id);
		model.addAttribute("event", event);
		model.addAttribute("user", user);
		model.addAttribute("users", event.getUsersAttending());
		List<Message> messages = event.getMessages();
		model.addAttribute("messages", messages);
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			model.addAttribute("error", "Invalid Credentials. Please try again.");
			return "view/events.jsp";
		} else {
			messageService.create(msg);
			msg.setUser(user);
			msg.setEvent(event);
			messageService.update(msg);
			messages.add(msg);
			event.setMessages(messages);
			user.setMessages(messages);
			return "redirect:/events/{id}";
		}
	}

//////////////////////////// Edit An Event Route ////////////////////////////////////////////////////////////	
	@RequestMapping("/events/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session,
			@Valid @ModelAttribute("emptyevent") Event emptyevent, BindingResult result) {
		Long uId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(uId);
		model.addAttribute("event", eventService.getEvent(id));
		model.addAttribute("user", user);
		model.addAttribute("usStates", usStates);
		return "view/editEvent.jsp";
	}

//////////////////////////// Edit An Event Method ////////////////////////////////////////////////////////////	
	@PostMapping("/events/{id}/edit")
	public String update(Model model, @PathVariable("id") Long id,
			@Valid @ModelAttribute("emptyevent") Event emptyevent, BindingResult result, HttpSession session) {
		model.addAttribute("usStates", usStates);
		Event event = eventService.getEvent(id);
		Long uId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(uId);
		model.addAttribute("event", event);
		model.addAttribute("user", user);
		if (result.hasErrors()) {
			return "/view/editEvent.jsp";
		} else {
			emptyevent.setHost(event.getHost());
			emptyevent.setUsersAttending(event.getUsersAttending());
			emptyevent.setCreatedAt(event.getCreatedAt());
			if (emptyevent.getDate() == null) {
				emptyevent.setDate(event.getDate());
			}
			eventService.update(emptyevent);
			return "redirect:/home";
		}
	}

//////////////////////////// Delete An Event Route ////////////////////////////////////////////////////////////	
	@RequestMapping("/events/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		Event event = eventService.getEvent(id);
		for (User user : event.getUsersAttending()) {
			List<Event> myevents = user.getEventsJoined();
			myevents.remove(event);
			user.setEventsJoined(myevents);
			userService.save(user);
		}
		for (Message message : messageService.allMessages()) {
			if (message.getEvent() == event) {
				messageService.delete(message.getId());
			}
		}
		eventService.delete(id);
		return "redirect:/home";
	}

/////////////////////////////// Logging Out Route //////////////////////////////////////////////////////////////
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
