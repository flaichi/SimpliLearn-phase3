package com.sportyshoes.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

	private SecurityUtils() {}

	public final static int TOKEN_VALIDITY = 30;
	
	public final static String TOKEN_PREFIX = "Bearer ";
	
	public final static String TOKEN_ISSUER = "Platz Manager Backend API";
	
	public final static String JWT_HEADER = "Authorization";
	
	public final static String PRIVATE_SECRET = "$2a$10$e3pM2b5ZqsMOTCrQGPKvqugy3pvzYmo/E7s2G/GtC5YX2XF1HedYG";
	
	
	public static Authentication getAuthUser() {
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		return securityContext.getAuthentication();
	}
		
	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public static String getAuthUserName() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth == null? "ANONYMOUS":auth.getName();
	}

	public static List<String> getAuthoritiesAsStringArray(){
		final List<String> authoritiesAsStringArray = new ArrayList<>();
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		auth.getAuthorities().stream().forEach(authority->
			authoritiesAsStringArray.add(authority.getAuthority())
		);
		return authoritiesAsStringArray;
	}

}
