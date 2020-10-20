package com.sportyshoes.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.entities.Customer;
import com.sportyshoes.entities.ItemsPurchased;
import com.sportyshoes.entities.Shoe;

public interface ItemsPurchasedRepository extends JpaRepository<ItemsPurchased, Long> {

	List<ItemsPurchased> findByCustomer(Customer customer);
	
	List<ItemsPurchased> findByShoe(Shoe shoe);
	
	List<ItemsPurchased> findByPurchasedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
