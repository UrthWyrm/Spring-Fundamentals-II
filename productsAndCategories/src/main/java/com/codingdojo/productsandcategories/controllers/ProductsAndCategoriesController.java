package com.codingdojo.productsandcategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.productsandcategories.models.Category;
import com.codingdojo.productsandcategories.models.Product;
import com.codingdojo.productsandcategories.services.CategoryService;
import com.codingdojo.productsandcategories.services.ProductService;

@Controller
public class ProductsAndCategoriesController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
////////////////////////////// Home //////////////////////////////////////
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("category", categoryService.all());
		model.addAttribute("product", productService.getAllProducts());
		return "view/index.jsp";
	}
////////////////////////////// New Category Constructor //////////////////////////////////////
	@RequestMapping("category/new")
	public String add(Model model, @ModelAttribute("category") Category category) {
		model.addAttribute("category", category);
		return "view/addCategory.jsp";
	}
//////////////////////////////New Category Method //////////////////////////////////////
	@PostMapping("category/new")
	public String create(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes errors) {
		if(result.hasErrors()) {
			errors.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/category/new";
		} else {
			categoryService.addCategory(category);
			return "redirect:/";
		}
	}
////////////////////////////// Show Category Constructor //////////////////////////////////////
	@RequestMapping("/category/addRelationship/{id}")
	public String viewCategory(Model model, @PathVariable("id") Long id) {
		Category category = categoryService.getCategory(id);
		List<Product> findAll = productService.getProductNotInCategory(category);
		model.addAttribute("product", findAll);
		model.addAttribute("category", category);
		return "view/showCategory.jsp";
	}
////////////////////////////// New Product Constructor //////////////////////////////////////
	@RequestMapping("product/new")
	public String show(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("category", categoryService.all());
		return "view/newProduct.jsp";
	}
////////////////////////////// New Product Method //////////////////////////////////////
	@PostMapping("product/new")
	public String add(@Valid @ModelAttribute("product") Product product, BindingResult result, RedirectAttributes errors) {
		if(result.hasErrors()) {
			errors.addFlashAttribute("errors", result.getAllErrors());
			return "redirect:/product/new";
		} else {
			productService.addProduct(product);
			return "redirect:/";
		}
	}
//////////////////////////////Show Product Constructor //////////////////////////////////////
		@RequestMapping("product/{id}")
		public String showCategory(Model model, @PathVariable("id") Long id) {
			Product product = productService.getProductById(id);
			List<Category> findAll = categoryService.getCategoryNotInProducts(product);
			model.addAttribute("product", product);
			model.addAttribute("category", findAll);
			return "view/showProduct.jsp";
		}
/////////////////////////////////// Show Product Method ///////////////////////////////////////////
		@PostMapping("product/addRelationship/{id}")
		public String addProduct(@PathVariable("id") Long productId, @RequestParam("category") Long categoryId) {
			Product product = productService.getProductById(productId);
			Category category = categoryService.getCategory(categoryId);
			List<Category> categories = product.getCategories();
			categories.add(category);
			productService.update(product);
			return "redirect:/product/"+productId;
		}
	}