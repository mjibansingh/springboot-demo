package com.miniproject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.model.ProductDTO;
import com.miniproject.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public Page<ProductDTO> getProduct(
			@RequestParam(required = false) String name, 
			Pageable pageable) {
		return productService.findByNameContaining(name, pageable);
	}

	@PostMapping
	public ProductDTO createProduct(
			@RequestBody @Valid ProductDTO product) {
		return productService.save(product);
	}

	@PutMapping("/{id}")
	public ProductDTO updateProduct(
			@PathVariable Long id, 
			@RequestBody @Valid ProductDTO payload) {
		payload.setId(payload.getId());
		return productService.update(payload);
	}

	@GetMapping("/{id}")
	public ProductDTO getProductById(
			@PathVariable Long id) {
		return productService.findById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(
			@PathVariable Long id) {
		return new ResponseEntity<>(productService.deleteById(id), HttpStatus.OK);
	}
}
