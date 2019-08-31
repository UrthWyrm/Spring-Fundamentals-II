package com.codingdojo.dojosandninjas.services;

import java.util.List;

import java.util.Optional;
import javax.persistence.Id;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.repositories.NinjaRepository;

	@Service
	public class NinjaService {
		@Autowired
		private NinjaRepository ninjaRepository;
		public NinjaService(NinjaRepository ninjaRepository) {
			this.ninjaRepository = ninjaRepository;
		}
		public void addNinja(Ninja ninja) {
			ninjaRepository.save(ninja);
		}
		public List<Ninja> getAllNinja(){
			return (List<Ninja>) ninjaRepository.findAll();
		}
		public void deleteNinjas(Long id) {
			ninjaRepository.deleteById(id);
		}
		public Ninja getNinjaById(Long id) {
			Optional<Ninja> optionalNinjas = ninjaRepository.findById(id);
	    	if(optionalNinjas.isPresent()) {
	            return optionalNinjas.get();
	        } else {
	            return null;
	        }
		}
}
