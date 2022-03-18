package com.karitoreyes.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karitoreyes.project.models.Category;
import com.karitoreyes.project.models.Product;
import com.karitoreyes.project.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<Category> getAllProducts() {
		return categoryRepo.findAll();
	}
	
	public void createNewCategory(Category category) {
		categoryRepo.save(category);
	}
	
	public Optional<Category> getById(Long id){
		return categoryRepo.findById(id);
	}
	
	public Iterable<Category> findAllByProducts(Product product) {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < product.getCategories().size(); i++) {
			ids.add(product.getCategories().get(i).getId());
		}
		return categoryRepo.findAllById(ids);
	}
	
	public Iterable<Category> findByProductsNotContains(Product product) {
		List<Category> categories = categoryRepo.findAll();
		for (int i = 0; i < product.getCategories().size(); i++) {
			for(int j=0; j < categories.size(); j++) {
				if(categories.get(j).getId() == product.getCategories().get(i).getId()) {
					categories.remove(j);
				}
			}
		}
		List<Long> no_contain_ids = new ArrayList<Long>();
		for (int i = 0; i < categories.size(); i++) {
			no_contain_ids.add(categories.get(i).getId());
		}
		return categoryRepo.findAllById(no_contain_ids);
	}
}