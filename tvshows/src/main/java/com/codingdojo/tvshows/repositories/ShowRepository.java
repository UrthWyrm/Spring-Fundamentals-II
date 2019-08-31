package com.codingdojo.tvshows.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.tvshows.models.Show;

public interface ShowRepository extends CrudRepository<Show, Long> {

}
