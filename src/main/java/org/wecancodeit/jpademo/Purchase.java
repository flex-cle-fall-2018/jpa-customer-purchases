package org.wecancodeit.jpademo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.junit.runners.ParentRunner;
import org.junit.runners.model.FrameworkMethod;

@Entity
public class Purchase {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String product;
	private int quantity;
	private double cost;
	
	@ManyToOne
	private Customer customer;

	protected Purchase() {}
	
	public Purchase(String product, int quantity, double cost, Customer customer) {
		this.product = product;
		this.quantity = quantity;
		this.cost = cost;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public String getProduct() {
		return product;
	}

	public Customer getCustomer() {
		return customer;
	}

}
