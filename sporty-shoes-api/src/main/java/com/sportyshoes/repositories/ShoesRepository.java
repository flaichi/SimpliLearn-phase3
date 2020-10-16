package com.sportyshoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.entities.Shoe;

public interface ShoesRepository extends JpaRepository<Shoe, Long> {

}
