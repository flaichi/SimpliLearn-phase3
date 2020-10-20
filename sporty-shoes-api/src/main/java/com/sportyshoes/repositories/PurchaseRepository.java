package com.sportyshoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.entities.Customer;
import com.sportyshoes.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	List<Purchase> findByCustomer(Customer customer);
}
