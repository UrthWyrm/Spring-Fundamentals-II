package com.codingdojo.productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.productsandcategories.models.Category;

public interface CategoryRepository extends CrudRepository <Category, Long> {
	List<Category> findAll();
	List<Category> findByNameNotIn(List<String> listofCategories);

}
