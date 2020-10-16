package com.sportyshoes.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.entities.Shoe;
import com.sportyshoes.repositories.ShoesRepository;

/**
 * This Class exposes the rest api.
 */
@RestController
@RequestMapping("/api/shoes")
public class ShoesController {
	
	/** The repository. */
	@Autowired
	private ShoesRepository repository;

	/**
	 * Gets the all shoes.
	 * 
	 * hit the following url in your browser: http;//localhost:8080/api/shoes
	 *
	 * @return the all shoes
	 */
	@GetMapping
	public List<Shoe> getAllShoes(){
		return repository.findAll();
	}
	
	/**
	 * Gets the one shoe.
	 *
	 * @param id the id
	 * @return the one shoe
	 */
	@GetMapping("/{id}")
	public Optional<Shoe> getOneShoe(@PathVariable Long id){
		return repository.findById(id);
	}
	
	/**
	 * Creates the shoe.
	 *
	 * @param shoe the shoe
	 * @return the shoe
	 */
	@PostMapping
	public Shoe createShoe(@RequestBody Shoe shoe) {
		return repository.save(shoe);
	}
}
