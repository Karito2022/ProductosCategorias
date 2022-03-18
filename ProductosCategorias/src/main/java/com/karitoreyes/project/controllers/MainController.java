package com.karitoreyes.project.controllers;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.karitoreyes.project.models.Category;
import com.karitoreyes.project.models.Product;
import com.karitoreyes.project.services.CategoryService;
import com.karitoreyes.project.services.ProductService;

@Controller
public class MainController {
	@Autowired
	private ProductService productServ;
	@Autowired
	private CategoryService categoryServ;
	
	@GetMapping("/products/new")
	public String newproduct(Model model) {
		model.addAttribute("newProduct", new Product());
		return "index.jsp";
	}
	
	@GetMapping("/categories/new")
	public String newcategory(Model model) {
		model.addAttribute("newCategory", new Category());
		return "newcategory.jsp";
	}
	
	@GetMapping("/products/{id}")
	public String productbyid(Model model, @PathVariable("id") Long id) {
		Optional<Product> product = productServ.getById(id);
		if(product == null) {
			return "redirect:/products/new";
		}
		Iterable<Category> categories_list = categoryServ.findAllByProducts(product.get());
		Iterable<Category> no_categories_list = categoryServ.findByProductsNotContains(product.get());
		model.addAttribute("categories_list", categories_list);
		model.addAttribute("no_categories_list", no_categories_list);
		model.addAttribute("product", product.get());
		return "productbyid.jsp";
	}
	
	@GetMapping("/categories/{id}")
	public String categorybyid(Model model, @PathVariable("id") Long id) {
		Optional<Category> category = categoryServ.getById(id);
		if(category == null) {
			return "redirect:/catergories/new";
		}
		Iterable<Product> product_list = productServ.findAllByCategory(category.get());
		Iterable<Product> no_product_list = productServ.findByCategoriesNotContains(category.get());
		model.addAttribute("product_list", product_list);
		model.addAttribute("no_product_list", no_product_list);
		model.addAttribute("category", category.get());
		return "categorybyid.jsp";
	}
	
	@PostMapping("/products/create")
	public String createproduct(
		@Valid @ModelAttribute("newProduct") Product newProduct, 
		BindingResult result, 
		Model model) {
	if(result.hasErrors()) {
	    return "index.jsp";
	}
	productServ.CreateNewProduct(newProduct);
	return "redirect:/products/new";
	}
	
	@PostMapping("/categories/create")
	public String createproduct(
		@Valid @ModelAttribute("newCategory") Category newCategory, 
		BindingResult result, 
		Model model) {
	if(result.hasErrors()) {
	    return "newcategory.jsp";
	}
	categoryServ.createNewCategory(newCategory);
	return "redirect:/categories/new";
	}
	
	@PostMapping("/products/addtoproduct")
	public String addtoproduct(
		@RequestParam("id") Long id,
		@RequestParam("option") Long option_id,
		Model model) {
	Category category = categoryServ.getById(option_id).get();
	Product product = productServ.getById(id).get();
	product.getCategories().add(category);
	productServ.CreateNewProduct(product);
	return "redirect:/products/"+id;
	}
	
	@PostMapping("/categories/addtocategory")
	public String addtocategory(
		@RequestParam("id") Long id,
		@RequestParam("option") Long option_id,
		Model model) {
	Product product = productServ.getById(option_id).get();
	Category category = categoryServ.getById(id).get();
	category.getProducts().add(product);
	categoryServ.createNewCategory(category);
	return "redirect:/categories/"+id;
	}

}