package com.sportyshoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
}
