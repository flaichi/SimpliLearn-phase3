package com.sportyshoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRoleName(String roleName);
}
