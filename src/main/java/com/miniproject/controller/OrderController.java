package com.miniproject.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.miniproject.entity.Category;
import com.miniproject.entity.CustomerOrder;
import com.miniproject.model.CategoryDTO;
import com.miniproject.model.CustomerOrderDTO;
import com.miniproject.service.CategoryService;
import com.miniproject.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderService orderService;

//	@GetMapping("/orders")
//	public Page<Order> getAllOrder(@RequestParam(required = false) String name, Pageable pageable) {
//		System.out.println("get all orders....");
//		return orderService.findByNameContaining(name, pageable);
//	}

//	@GetMapping("/orders/{id}")
//	public Order getOrderById(@PathVariable("id") int id) {
//		return orderService.findById(id);
//	}

	@PostMapping("/orders")
	public CustomerOrderDTO createOrder(@RequestBody @Valid CustomerOrderDTO order) {
		return orderService.save(order);
	}

//	@PutMapping("/orders/{id}")
//	public Order updateOrder(@PathVariable("id") int id, @RequestBody Order order) {
//		order.setOrderId(id);
//		return orderService.update(order);
//	}

//	@DeleteMapping("/orders/{id}")
//	public ResponseEntity<String> deleteOrder(@PathVariable("id") int id) {
//		return new ResponseEntity<>(orderService.deleteById(id), HttpStatus.OK);
//	}
}
