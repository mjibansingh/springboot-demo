package com.miniproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.entity.Category;

public interface CatRepo extends JpaRepository<Category, Long> {

	@Query("SELECT c from Category c WHERE (:name is null or c.name like %:name%)")
	Page<Category> findByNameContaining(String name, Pageable pageable);
	
	int countByName(String name);
	
	int countByNameAndIdNot(String name, Long id);
	
}
