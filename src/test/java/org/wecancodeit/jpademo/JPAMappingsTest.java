package org.wecancodeit.jpademo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	CustomerRepository customerRepo;

	@Resource
	PurchaseRepository purchaseRepo;
	
	@Resource
	EntityManager entityManager;
	
	@Test
	public void shouldSaveAndLoadCustomer() {
		Customer customer = customerRepo.save(
			new Customer("Josh", "123 Main St", "555-5555")
		);
		long customerId = customer.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Customer> result = customerRepo.findById(customerId);
		Customer resultCustomer = result.get();
		assertThat(resultCustomer.getName(), is("Josh"));
	}
	

	@Test
	public void shouldSaveAndLoadPurchase() {
		Purchase purchase= purchaseRepo.save(
			new Purchase("Sword", 3, 250.00, null) // TODO: Customer
		);
		long purchaseId = purchase.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Purchase> result = purchaseRepo.findById(purchaseId);
		Purchase resultPurchase = result.get();
		assertThat(resultPurchase.getProduct(), is("Sword"));
	}
	
	@Test
	public void shouldEstablishCustomerPurchaseRelationship() {
		Customer customer = customerRepo.save(new Customer("Josh", "123 Main St", "555-5555"));
		Purchase purchase1 = purchaseRepo.save(new Purchase("Sword", 3, 250.00, customer));
		Purchase purchase2 = purchaseRepo.save(new Purchase("iPhone", 1, 1000.00, customer));
		long customerId = customer.getId();
		long purchaseId1 = purchase1.getId();
		long purchaseId2 = purchase2.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Customer> customerOptional = customerRepo.findById(customerId);
		Optional<Purchase> purchaseOptional1 = purchaseRepo.findById(purchaseId1);
		Optional<Purchase> purchaseOptional2 = purchaseRepo.findById(purchaseId2);
		Customer resultCustomer= customerOptional.get();
		Purchase resultPurchase1 = purchaseOptional1.get();
		Purchase resultPurchase2= purchaseOptional2.get();
		assertThat(resultPurchase1.getCustomer().getName(), is("Josh"));
		assertThat(resultCustomer.getPurchases(), containsInAnyOrder(resultPurchase1, resultPurchase2));
	}

}
