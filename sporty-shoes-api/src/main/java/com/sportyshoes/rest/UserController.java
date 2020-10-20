package com.sportyshoes.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportyshoes.entities.User;
import com.sportyshoes.entities.UserForm;
import com.sportyshoes.services.AccountService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/api/accounts")
public class UserController {

	/** The service. */
	@Autowired
	private AccountService service;
	
	/**
	 * Register user.
	 *
	 * @param userForm the user form
	 * @return the user
	 */
	@PostMapping("/register-user")
	public User registerUser(@RequestBody UserForm userForm) {
		return service.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
	}
	
	/**
	 * Register admin.
	 *
	 * @param userForm the user form
	 * @return the user
	 */
	@PostMapping("/register-admin")
	public User registerAdmin(@RequestBody UserForm userForm) {
		return service.saveAdmin(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
	}
	
}

