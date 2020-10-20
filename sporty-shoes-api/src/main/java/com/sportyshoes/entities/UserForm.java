package com.sportyshoes.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class UserForm {

	private String username;
	
	private String password;
	
	private String confirmedPassword;
}
