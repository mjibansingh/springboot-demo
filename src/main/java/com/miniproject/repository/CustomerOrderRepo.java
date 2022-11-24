package com.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.miniproject.entity.CustomerOrder;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder, Long> {
	
//	@Query("SELECT c from Category c WHERE (:categoryName is null or c.categoryName like '%:categoryName%')")
//	Page<Order> findByNameContaining(String orderName, Pageable pageable);
	
	//int countByOrderId(int orderId);
	
	//int countByOrderNameAndOrderIdNot(String orderName, int orderId);


}
