package com.sportyshoes.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.entities.Customer;
import com.sportyshoes.entities.ItemsPurchased;
import com.sportyshoes.entities.Shoe;
import com.sportyshoes.repositories.CustomerRepository;
import com.sportyshoes.repositories.ItemsPurchasedRepository;
import com.sportyshoes.repositories.ShoesRepository;


/**
 * This Class exposes the rest api.
 */
@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ShoesRepository shoeRepository;
	
	
	@Autowired
	private ItemsPurchasedRepository ipRepository;
	
	@PostMapping("/{customerId}/{itemId}")
	public ItemsPurchased purchaseItem(@PathVariable Long customerId, @PathVariable Long itemId) {
		final Customer customer = customerRepository.findById(customerId).orElse(null);
		final Shoe shoe = shoeRepository.findById(itemId).orElse(null);
		final ItemsPurchased itemsPurchased = new ItemsPurchased();
		itemsPurchased.setCode(UUID.randomUUID().toString());
		itemsPurchased.setCustomer(customer);
		itemsPurchased.setShoe(shoe);
		itemsPurchased.setPurchasedDate(LocalDateTime.now());
		return ipRepository.save(itemsPurchased);
	}
	
	@GetMapping("/by-user/{customerId}")
	public List<ItemsPurchased> purchasedByCustomer(@PathVariable Long customerId){
		final Customer customer = customerRepository.findById(customerId).orElse(null);
		return ipRepository.findByCustomer(customer);
	}
	
	@GetMapping("/by-item/{itemId}")
	public List<ItemsPurchased> purchasedByItem(@PathVariable Long itemId){
		final Shoe shoe = shoeRepository.findById(itemId).orElse(null);
		return ipRepository.findByShoe(shoe);
	}
	
	@GetMapping("/by-dates/{startDate}/{endDate}")
	public List<ItemsPurchased> purchasedByItem(@PathVariable String startDate,@PathVariable String endDate){
		System.out.println(startDate);
		final LocalDateTime date1 = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		final LocalDateTime date2 = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		return ipRepository.findByPurchasedDateBetween(date1, date2);
	}
}
