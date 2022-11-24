package com.miniproject.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.entity.CustomerOrder;
import com.miniproject.entity.CustomerOrderItem;
import com.miniproject.model.CustomerOrderDTO;
import com.miniproject.model.CustomerOrderItemDTO;
import com.miniproject.repository.CustomerOrderItemRepo;
import com.miniproject.repository.CustomerOrderRepo;
import com.miniproject.repository.ProdRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CustomerOrderRepo orderRepo;
	
	@Autowired
	CustomerOrderItemRepo orderItemRepo;
	
	@Autowired
	ProdRepo prodRepo;

	@Override 
	@Transactional
	public CustomerOrderDTO save(CustomerOrderDTO payload) { // name validation int
//		int count = orderRepo.countByOrderId(payload.getOrderId());
//		if (count > 0) {
//			throw new RuntimeException("Order id existed.");
//		}

		CustomerOrder order = CustomerOrder.builder()
				.timeStamp(new Date())
				.build();
		orderRepo.save(order);
		
		List<CustomerOrderItem> items = new ArrayList<>();
		for(CustomerOrderItemDTO itr : payload.getItems()) {
			CustomerOrderItem item = CustomerOrderItem.builder()
					.customerOrder(order)
					.product(prodRepo.findById(itr.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found.")))
					.quantity(itr.getQuantity())
					.price(itr.getPrice())
					.build();
			items.add(item);
		}
		orderItemRepo.saveAll(items);
		
		return new CustomerOrderDTO(order);
	}

	@Override 
	public CustomerOrder update(CustomerOrder order) { 
		CustomerOrder loaded = orderRepo.findById(order.getId()).orElseThrow(() -> new RuntimeException("Order not found."));
		BeanUtils.copyProperties(order, loaded);
		return orderRepo.save(loaded);
				
	}
 
  
// Name validation int count =
// orderRepo.countByProductNameAndProductIdNot(product.getProductId(),
// product.getProductName()); if (count > 0) { throw new
// RuntimeException("Order's name existed."); }
// 
// BeanUtils.copyProperties(product, loaded); return prodRepo.save(loaded); }
//
//	@Override
//	public Order findById(int id) {
//		return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Product's ID not found."));
//	}
//
	@Override
	public String deleteById(Long id) {
		CustomerOrder loaded = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Product's ID not found."));
		orderRepo.delete(loaded);
		return "Successfully deleted.";
	}

//	@Override
//	public Page<Order> findByNameContaining(String name, Pageable pageable) {
//		System.err.println("search...");
//		return orderRepo.findByNameContaining(name, pageable);
//	}
//	// @PostConstruct void test() { // database initiase // Category category =
//	Category.builder().categoryName(new Date().toGMTString()).build(); //
//
////	save(category); }
	void test() {
		// database initiase
//		Category category = Category.builder().categoryName(new Date().toGMTString()).build();
//		save(category);
	}

	@Override
	public CustomerOrder findById(Long id) {
		// TODO Auto-generated method stub
		return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Product's ID not found."));
	}
	
	
}
