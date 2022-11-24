package com.miniproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miniproject.model.CategoryDTO;

public interface CategoryService {
	
	CategoryDTO save(CategoryDTO category);

	CategoryDTO update(CategoryDTO category);

	CategoryDTO findById(Long id);

	String deleteById(Long id);

	Page<CategoryDTO> findByNameContaining(String name, Pageable pageable);

}
