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

import com.miniproject.model.CategoryDTO;
import com.miniproject.service.CategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {
	//Sample API - See API Documentation
	//POST		localhost:8080/category
	//PUT		localhost:8080/category
	//GET		localhost:8080/category?page=0&size=10&name=Cat&sort=name,asc
	//GET		localhost:8080/category/1
	//DELETE	localhost:8080/category/1

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public Page<CategoryDTO> getAllCategory(
			@RequestParam(required = false) String name, 
			Pageable pageable) {
		return categoryService.findByNameContaining(name, pageable);
	}

	@GetMapping("/{id}")
	public CategoryDTO getCategoryById(
			@PathVariable Long id) {
		return categoryService.findById(id);
	}

	@PostMapping
	public CategoryDTO createCategory(
			@RequestBody @Valid CategoryDTO category) {
		return categoryService.save(category);
	}

	@PutMapping("/{id}")
	public CategoryDTO updateCategory(
			@PathVariable Long id, 
			@RequestBody CategoryDTO payload) {
		payload.setId(id);
		return categoryService.update(payload);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(
			@PathVariable Long id) {
		return new ResponseEntity<>(categoryService.deleteById(id), HttpStatus.OK);
	}

}