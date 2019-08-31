package com.codingdojo.tvshows.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.tvshows.models.Show;
import com.codingdojo.tvshows.repositories.ShowRepository;

@Service
public class ShowService {
	@Autowired
	private ShowRepository showRepository; 
		public ShowService(ShowRepository showRepository) {
			this.showRepository = showRepository;
		}
	public void addShow(Show show) {
		showRepository.save(show);
	}
	public List<Show> all() {
		return (List<Show>) showRepository.findAll();
	}
	public Show getShow(Long id) {
		Optional<Show> optional = showRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	public void update(Show show) {
		showRepository.save(show);
	}
	public void delete(Long id) {
		showRepository.deleteById(id);
	}
	public void create(@Valid Show show) {
		showRepository.save(show);
	}
}
