package com.codingdojo.productsandcategories.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.productsandcategories.models.Category;
import com.codingdojo.productsandcategories.models.Product;
import com.codingdojo.productsandcategories.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	public List<Category> all() {
		return categoryRepository.findAll();
	}

	public Category getCategory(Long id) {
		Optional<Category> optional = categoryRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public List<Category> getCategoryNotInProducts(Product product) {
		if (product.getCategories().size() == 0)
			return categoryRepository.findAll();
		
		List<String> listInProduct = new ArrayList<String>();
		for(Category c : product.getCategories()) {
			listInProduct.add(c.getName());
		}
		return categoryRepository.findByNameNotIn(listInProduct);
	}

}
