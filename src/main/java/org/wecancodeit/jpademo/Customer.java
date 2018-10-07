package org.wecancodeit.jpademo;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	String name;
	String address;
	String phone;
	
	@OneToMany(mappedBy = "customer")
	private Collection<Purchase> purchases;	
	
	// JPA
	protected Customer() {}

	public Customer(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}

	public Collection<Purchase> getPurchases() {
		return purchases;
	}

}
