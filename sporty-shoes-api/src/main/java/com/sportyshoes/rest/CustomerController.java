package com.sportyshoes.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.entities.Customer;
import com.sportyshoes.repositories.CustomerRepository;


/**
 * This Class exposes the rest api.
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	/** The repository. */
	@Autowired
	private CustomerRepository repository;
	

	/**
	 * Gets the all Customers.
	 * 
	 * hit the following url in your browzer: http;//localhost:8086/api/Customers
	 *
	 * @return the all Customers
	 */
	@GetMapping
	public List<Customer> getAllCustomers(){
		return repository.findAll();
	}
	
	/**
	 * Gets the one Customer.
	 *
	 * @param id the id
	 * @return the one Customer
	 */
	// get customer by ID
	@GetMapping("/{id}")
	public Optional<Customer> getOneCustomer(@PathVariable Long id){
		return repository.findById(id);
	}
	
	/**
	 * Creates the Customer.
	 *
	 * @param Customer the Customer
	 * @return the Customer
	 */
	@PostMapping
	public Customer createCustomer(@RequestBody Customer customer) {
		customer.setRegisterDate(LocalDateTime.now());
		return repository.save(customer);
	}
	
	// update the customer 
	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
		customer.setId(id);
		return repository.save(customer);
	}
	
	/**
	 * Search by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	@GetMapping("/search/{name}")
	public List<Customer> searchByName(@PathVariable String name){
		// [% + name + %] makes the query look for the customers' name that contains the string name
		return repository.findByNameLike("%"+name+"%");
	}
}
