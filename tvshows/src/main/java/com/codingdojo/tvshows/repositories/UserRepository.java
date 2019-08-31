package com.codingdojo.tvshows.repositories;


import org.springframework.data.repository.CrudRepository;

import com.codingdojo.tvshows.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

}
