package com.codingdojo.driverslicense.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.driverslicense.models.User;

public interface UsersRepository extends CrudRepository <User, Long> {

}
