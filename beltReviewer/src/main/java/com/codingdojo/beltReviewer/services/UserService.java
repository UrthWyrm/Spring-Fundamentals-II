package com.codingdojo.beltReviewer.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.beltReviewer.models.User;
import com.codingdojo.beltReviewer.repositories.UserRepository;

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
/////////////// Find User By Email /////////////////////////////////////

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

/////////////// Finds User By Id /////////////////////////////////////
	public User findUserById(Long id) {
		Optional<User> u = userRepository.findById(id);

		if (u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}

/////////////// Authenticates User /////////////////////////////////////
	public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
//////// if the passwords match, return true, else, return false ///////
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
/////////////////// Updating A User //////////////////////////////////
	public void update(User user) {
		userRepository.save(user);
		
	}
	
//////////////////// Find All Users //////////////////////////////////	
	public List<User> allUsers() {
    	return (List<User>) userRepository.findAll();
    }

}
