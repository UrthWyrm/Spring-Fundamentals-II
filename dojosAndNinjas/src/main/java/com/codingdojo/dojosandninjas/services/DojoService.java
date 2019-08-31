package com.codingdojo.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.repositories.DojoRepository;

@Service
public class DojoService {
	@Autowired
	private DojoRepository dojoRepository;
	public DojoService(DojoRepository dojoRepository) {
		this.dojoRepository = dojoRepository;
	}
	public void addDojo(Dojo dojo) {
		dojoRepository.save(dojo);
	}
	public List<Dojo> all(){
		return (List<Dojo>) dojoRepository.findAll();
	}
	public Dojo getDojo(Long id) {
		Optional<Dojo> opt = dojoRepository.findById(id);
		if(opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
	}
	private Dojo dojoRepository(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
