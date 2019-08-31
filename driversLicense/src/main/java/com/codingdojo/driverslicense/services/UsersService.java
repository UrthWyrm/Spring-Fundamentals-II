package com.codingdojo.driverslicense.services;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.codingdojo.driverslicense.models.User;
import com.codingdojo.driverslicense.repositories.UsersRepository;

@Service
public class UsersService {
	private UsersRepository usersRepository;
	private UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	public void addUsers(User users) {
		usersRepository.save(users);
	}
	public List<User> getAllUsers(){
		return (List<User>) usersRepository.findAll();
	}
	public void deleteUsers(Long id) {
		usersRepository.deleteById(id);
	}
	public User getUserById(Long id) {
		Optional<User> optionalUsers = usersRepository.findById(id);
    	if(optionalUsers.isPresent()) {
            return optionalUsers.get();
        } else {
            return null;
        }	}

}
