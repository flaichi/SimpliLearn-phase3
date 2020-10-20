package com.sportyshoes.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sportyshoes.entities.User;
import com.sportyshoes.services.AccountService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = accountService.loadUserByUsername(username);
		if(user == null) throw new UsernameNotFoundException(username+ " is not a user");
		final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
