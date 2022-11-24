package com.miniproject.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.miniproject.model.CustomerOrderDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderItem {
	
	public CustomerOrderItem(CustomerOrderDTO payload) {
		super();
		BeanUtils.copyProperties(payload, this);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customerOrder", nullable=false)
	@JsonProperty(access=Access.WRITE_ONLY)
	private CustomerOrder customerOrder;

	@ManyToOne
	@JoinColumn(name="product", nullable=false)
	private Product product;

	private Integer quantity;

	private Float price;


}
