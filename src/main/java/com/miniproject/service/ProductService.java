package com.miniproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.miniproject.model.ProductDTO;

public interface ProductService {

	Page<ProductDTO> findByNameContaining(String name, Pageable pageable);
	
	ProductDTO save(ProductDTO product);

	Object deleteById(Long id);

	ProductDTO findById(Long id);

	ProductDTO update(ProductDTO product);

}

