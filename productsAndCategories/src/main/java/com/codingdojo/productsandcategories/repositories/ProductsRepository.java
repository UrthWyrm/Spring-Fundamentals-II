package com.codingdojo.productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.productsandcategories.models.Product;

public interface ProductsRepository extends CrudRepository <Product, Long> {
	List<Product> findAll();
	List<Product> findByNameNotIn(List<String> listofProducts);

}
