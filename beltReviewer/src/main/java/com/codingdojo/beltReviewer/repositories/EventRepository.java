package com.codingdojo.beltReviewer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.beltReviewer.models.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
