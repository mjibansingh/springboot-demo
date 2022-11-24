package com.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.miniproject.entity.CustomerOrderItem;

public interface CustomerOrderItemRepo extends JpaRepository<CustomerOrderItem, Long> {
	

}
