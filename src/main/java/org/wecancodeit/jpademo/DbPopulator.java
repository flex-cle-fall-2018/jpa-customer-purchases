package org.wecancodeit.jpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbPopulator implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	PurchaseRepository purchaseRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Customer bob = customerRepo.save(new Customer("Bob", "555 Pine St", "123-4567"));
		Purchase swords = purchaseRepo.save(new Purchase("Sword", 3, 222.22, bob));
		Purchase lawnmower = purchaseRepo.save(new Purchase("Lawnmower", 2, 1500.00, bob));
	}

}
