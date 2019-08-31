package com.codingdojo.productsandcategories.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.productsandcategories.models.Category;
import com.codingdojo.productsandcategories.models.Product;
import com.codingdojo.productsandcategories.repositories.ProductsRepository;

@Service
public class ProductService {
	@Autowired
	private ProductsRepository productsRepository;
	public ProductService(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}
	public void addProduct(Product product) {
		productsRepository.save(product);
	}
	public List<Product> getAllProducts(){
		return productsRepository.findAll();
	}
	public void deleteProducts(Long id) {
		productsRepository.deleteById(id);
	}
	public Product getProductById(Long id) {
		Optional<Product> optionalProducts = productsRepository.findById(id);
		if(optionalProducts.isPresent()) {
			return optionalProducts.get();
		} else {
			return null;
		}
	}
	public void update(Product product) {
		productsRepository.save(product);
		
	}
	public List<Product> getProductNotInCategory(Category category) {
		if (category.getProducts().size() == 0)
			return productsRepository.findAll();
		List<String> listInCategory = new ArrayList<String>();
		for(Product c : category.getProducts()) {
			listInCategory.add(c.getName());
			}
		return productsRepository.findByNameNotIn(listInCategory);	
		}
	

}
