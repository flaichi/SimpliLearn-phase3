package com.sportyshoes.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sportyshoes.entities.ItemsPurchased;
import com.sportyshoes.entities.Shoe;
import com.sportyshoes.entities.User;

public interface ItemsPurchasedRepository extends JpaRepository<ItemsPurchased, Long> {

	List<ItemsPurchased> findByUser(User user);
	
	List<ItemsPurchased> findByShoe(Shoe shoe);
	
	List<ItemsPurchased> findByPurchasedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	//List<ItemsPurchased> findByPurchasedDateBetweenAndGender(LocalDateTime startDate, LocalDateTime endDate, String gender);

}
