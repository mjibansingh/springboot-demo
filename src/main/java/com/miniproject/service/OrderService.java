package com.miniproject.service;

import com.miniproject.entity.CustomerOrder;
import com.miniproject.model.CustomerOrderDTO;

public interface OrderService {

	CustomerOrderDTO save(CustomerOrderDTO product);

	Object deleteById(Long id);

	CustomerOrder findById(Long id);

	CustomerOrder update(CustomerOrder product);

	// List<Category> findAll(); // // int insert(Category category);

	//Page<Order> findByNameContaining(String name, Pageable pageable);

	// int deleteAll(); }
	
	//Order update(Order order);
}