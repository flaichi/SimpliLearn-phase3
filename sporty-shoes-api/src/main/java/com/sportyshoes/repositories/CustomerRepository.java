package com.sportyshoes.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByNameLike(String name);
}

