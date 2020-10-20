package com.sportyshoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.entities.Role;
import com.sportyshoes.entities.User;
import com.sportyshoes.repositories.RoleRepository;
import com.sportyshoes.repositories.UserRepository;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User saveUser(String username, String password, String confirmedPassword) {
		User user = userRepository.findByUsername(username);
		if(user != null) throw new RuntimeException("the user already exists");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("the password and confirmation don't match");
		user = new User();
		user.setPassword(passwordEncoder.encode(password));
		user.setUsername(username);
		userRepository.save(user);
		//better to put those values in a static class, or an enum. always try to avoid hard-coded values
		addRoleToUser(username, "USER_READ");
		addRoleToUser(username, "USER_WRITE");
		return user;
	}
	
	public User saveAdmin(String username, String password, String confirmedPassword) {
		User user = userRepository.findByUsername(username);
		if(user != null) throw new RuntimeException("the user already exists");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("the password and confirmation don't match");
		user = new User();
		user.setPassword(passwordEncoder.encode(password));
		user.setUsername(username);
		userRepository.save(user);
		//better to put those values in a static class, or an enum. always try to avoid hard-coded values
		addRoleToUser(username, "ADMIN_READ");
		addRoleToUser(username, "ADMIN_WRITE");
		return user;
	}
	
	public Role saveRole(String roleName) {
		final Role role = new Role();
		role.setRoleName(roleName);
		return roleRepository.save(role);
	}
	
	public User loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void addRoleToUser(String username, String roleName) {
		final User user = userRepository.findByUsername(username);
		final Role role = roleRepository.findByRoleName(roleName);
		user.getRoles().add(role);
	}
}
