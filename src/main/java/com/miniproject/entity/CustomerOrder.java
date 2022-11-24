package com.miniproject.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.beans.BeanUtils;

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
public class CustomerOrder {
	
	public CustomerOrder(CustomerOrderDTO payload) {
		super();
		BeanUtils.copyProperties(payload, this);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date timeStamp;

	@OneToMany(mappedBy = "customerOrder")
	private List<CustomerOrderItem> items = new ArrayList<>();
}
