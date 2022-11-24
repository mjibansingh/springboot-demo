package com.miniproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miniproject.entity.Product;
@Repository

public interface ProdRepo extends JpaRepository<Product, Long> {

	@Query("SELECT p from Product p WHERE (:name is null or p.name like %:name%)")
	Page<Product> findByNameContaining(String name, Pageable pageable);
	
	int countByName(String name);
	
	int countByNameAndIdNot(String name, Long id);

}
