package com.codingdojo.beltReviewer.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.beltReviewer.models.Event;
import com.codingdojo.beltReviewer.repositories.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository; 
		public EventService(EventRepository eventRepository) {
			this.eventRepository = eventRepository;
		}
	public void addEvent(Event event) {
		eventRepository.save(event);
	}
	public List<Event> all() {
		return (List<Event>) eventRepository.findAll(); 
	}
	public Event getEvent(Long id) {
		Optional<Event> optional = eventRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	public void update(Event event) {
		eventRepository.save(event);
		
	}
	public void delete(Long id) {
		eventRepository.deleteById(id);
		
	}
	public void create(@Valid Event event) {
		eventRepository.save(event);
		
	}

}
