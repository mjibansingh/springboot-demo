package com.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.miniproject.entity.Product;
import com.miniproject.model.ProductDTO;
import com.miniproject.repository.ProdRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProdRepo prodRepo;


	@Override
	public Page<ProductDTO> findByNameContaining(String name, Pageable pageable) {
		Page<Product> pages = prodRepo.findByNameContaining(name, pageable);
		
		List<ProductDTO> result = new ArrayList<>();
		for(Product itr : pages.getContent()) {
			result.add(new ProductDTO(itr));
		}
		return new PageImpl<>(result, pageable, pages.getTotalElements()); 
	}

	@Override
	public String deleteById(Long id) {
		Product loaded = prodRepo.findById(id).orElseThrow(() -> new RuntimeException("Product's ID not found."));

		prodRepo.delete(loaded);
		return "Successfully deleted.";
	}

	@Override
	public ProductDTO findById(Long id) {
		Product product = prodRepo.findById(id).orElseThrow(()->new RuntimeException("Product's ID not found."));
		
		return new ProductDTO(product);
	}

	@Override 
	public ProductDTO update(ProductDTO payload) { 
		
		Product product = prodRepo.findById(payload.getId()).orElseThrow(()->new RuntimeException("Product's ID not found."));

		//Name validation
		int count = prodRepo.countByNameAndIdNot(payload.getName(), payload.getId());
		if(count > 0) {
			throw new RuntimeException("Category's name existed.");
		}
		
		product = prodRepo.save(new Product(payload));
		
		return new ProductDTO(product);
				
	}
			
	@Override
	public ProductDTO save(ProductDTO payload) { 
		
		//Validation
		int count = prodRepo.countByName(payload.getName());
		if (count > 0) {
			throw new RuntimeException("Product's name existed.");
		}

		Product product = prodRepo.save(new Product(payload));
		
		return new ProductDTO(product);
	}

	// @PostConstruct
	void test() {
		// database initiase
//		Category category = Category.builder().categoryName(new Date().toGMTString()).build();
//		save(category);
	}

}
