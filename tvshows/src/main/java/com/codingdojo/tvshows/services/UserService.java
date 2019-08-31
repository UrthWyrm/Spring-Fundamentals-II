package com.codingdojo.tvshows.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.tvshows.models.User;
import com.codingdojo.tvshows.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
/////////////// Register User And Hash Password /////////////////////////////////////
	public User save(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepository.save(user);
	}
/////////////// Find Users By Email /////////////////////////////////////

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
/////////////// Find Users By Id /////////////////////////////////////
	
	public User findUserById(Long id) {
		Optional<User> u = userRepository.findById(id);
		
		if (u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}
/////////////// Authenticate User /////////////////////////////////////
	
	public boolean authenticateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return false;
		} else {
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}

/////////////// Updating Users /////////////////////////////////////

	public void update(User user) {
		userRepository.save(user);
	}
/////////////// Finding All Users /////////////////////////////////////
	
	public List<User> allUsers() {
		return (List<User>) userRepository.findAll();
	}
}

