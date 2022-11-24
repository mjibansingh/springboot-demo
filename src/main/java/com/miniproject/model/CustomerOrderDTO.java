package com.miniproject.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.miniproject.entity.CustomerOrder;
import com.miniproject.entity.CustomerOrderItem;
import com.miniproject.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerOrderDTO {
	
	public CustomerOrderDTO(CustomerOrder payload) {
		super();
		BeanUtils.copyProperties(payload, this);
		//TODO
	}
	
	private Long id;

	private Product product;

	private int quantity;

	private float price;

	private Date timeStamp;
	
	private List<CustomerOrderItemDTO> items = new ArrayList<>();
	

}
