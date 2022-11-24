package com.miniproject.model;

import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderItemDTO {
	
	public CustomerOrderItemDTO(CustomerOrderDTO payload) {
		super();
		BeanUtils.copyProperties(payload, this);
		this.product = new ProductDTO(payload.getProduct());
	}


	private Long id;
	

	private ProductDTO product;

	private int quantity;

	private float price;


}
