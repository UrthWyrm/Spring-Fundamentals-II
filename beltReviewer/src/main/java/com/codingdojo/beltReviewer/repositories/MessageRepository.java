package com.codingdojo.beltReviewer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.beltReviewer.models.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

}
