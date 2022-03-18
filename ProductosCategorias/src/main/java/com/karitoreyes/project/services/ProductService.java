package com.karitoreyes.project.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karitoreyes.project.models.Category;
import com.karitoreyes.project.models.Product;
import com.karitoreyes.project.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;

	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	public void CreateNewProduct(Product product) {
		productRepo.save(product);
	}
	
	public Optional<Product> getById(Long id){
		return productRepo.findById(id);
	}
	
	public Iterable<Product> findAllByCategory(Category category) {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < category.getProducts().size(); i++) {
			ids.add(category.getProducts().get(i).getId());
		}
		return productRepo.findAllById(ids);
	}
	
	public Iterable<Product> findByCategoriesNotContains(Category category) {
		List<Product> products = productRepo.findAll();
		for (int i = 0; i < category.getProducts().size(); i++) {
			for(int j=0; j < products.size(); j++) {
				if(products.get(j).getId() == category.getProducts().get(i).getId()) {
					products.remove(j);
				}
			}
		}
		List<Long> no_contain_ids = new ArrayList<Long>();
		for (int i = 0; i < products.size(); i++) {
			no_contain_ids.add(products.get(i).getId());
		}
		return productRepo.findAllById(no_contain_ids);
	}
}