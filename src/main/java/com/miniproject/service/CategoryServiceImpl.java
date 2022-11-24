package com.miniproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.miniproject.entity.Category;
import com.miniproject.entity.Product;
import com.miniproject.model.CategoryDTO;
import com.miniproject.model.ProductDTO;
import com.miniproject.repository.CatRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CatRepo catRepo;

	@Override
	public CategoryDTO save(CategoryDTO payload) {
		// name validation
		int count = catRepo.countByName(payload.getName());
		if(count > 0) {
			throw new RuntimeException("Category's name existed.");
		}
		
		Category category = catRepo.save(new Category(payload));
		
		return new CategoryDTO(category);
	}

	@Override
	public CategoryDTO update(CategoryDTO payload) {
		Category category = catRepo.findById(payload.getId()).orElseThrow(()->new RuntimeException("Category's ID not found."));
		
		//Name validation
		int count = catRepo.countByNameAndIdNot(category.getName(), category.getId());
		if(count > 0) {
			throw new RuntimeException("Category's name existed.");
		}
		
		category = catRepo.save(new Category(payload));		

		return new CategoryDTO(category);
	}

	@Override
	public CategoryDTO findById(Long id) {
		Category category = catRepo.findById(id).orElseThrow(()->new RuntimeException("Category's ID not found."));
		
		return new CategoryDTO(category); 
	}

	@Override
	public String deleteById(Long id) {
		Category loaded = catRepo.findById(id).orElseThrow(()->new RuntimeException("Category's ID not found."));
		
		//products existed;
		if(loaded.getProducts().size() != 0) {
			throw new RuntimeException("Products existed.");
		}
		
		catRepo.delete(loaded);
		
		return "Successfully deleted.";
	}



	@Override
	public Page<CategoryDTO> findByNameContaining(String name, Pageable pageable) {
		System.err.println("name > " + name);
		Page<Category> pages = catRepo.findByNameContaining(name, pageable);
		
		List<CategoryDTO> result = new ArrayList<>();
		for(Category itr : pages.getContent()) {
			result.add(new CategoryDTO(itr));
		}
		return new PageImpl<>(result, pageable, pages.getTotalElements()); 
	}


	//@PostConstruct
	void test() {
		//database initiase
//		Category category = Category.builder().categoryName(new Date().toGMTString()).build();
//		save(category);
	}

}
