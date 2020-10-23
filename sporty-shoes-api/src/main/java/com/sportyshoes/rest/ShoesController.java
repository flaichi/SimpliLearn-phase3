package com.sportyshoes.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.entities.Shoe;
import com.sportyshoes.entities.ShoesFilter;
import com.sportyshoes.repositories.ShoeDao;
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
	
	@Autowired
	private ShoeDao dao;

	/**
	 * Gets the all shoes.
	 * 
	 * hit the following url in your browzer: http;//localhost:8080/api/shoes
	 *
	 * @return the all shoes
	 */
	//
	@PreAuthorize("hasAuthority('ADMIN_READ')")
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
	@PreAuthorize("hasAuthority('ADMIN_WRITE')")
	@PostMapping
	public Shoe createShoe(@RequestBody Shoe shoe) {
		return repository.save(shoe);
	}
	
	@PutMapping("/{id}")
	public Shoe updateShoe(@RequestBody Shoe shoe, @PathVariable Long id) {
		shoe.setId(id);
		return repository.save(shoe);
	}
	
	@PostMapping("/search")
	public List<Shoe> searchShoes(@RequestBody ShoesFilter filter){
		return dao.advancedShoesResearch(filter);
	}
}
