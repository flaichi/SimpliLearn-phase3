package com.sportyshoes.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportyshoes.entities.User;



public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager auth;
	
	public JWTAuthenticationFilter(AuthenticationManager auth) {
		this.auth = auth;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		try{
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			return auth
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		List<String> roles = new ArrayList<>();
		authResult.getAuthorities().forEach(authority->{
			roles.add(authority.getAuthority());
		});
		Date expiration = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(expiration); 
		c.add(Calendar.DATE, SecurityUtils.TOKEN_VALIDITY);
		expiration = c.getTime();
		final String jwt = JWT.create()
							.withIssuer(SecurityUtils.TOKEN_ISSUER)
							.withIssuedAt(new Date())
							.withSubject(user.getUsername())
							.withArrayClaim("roles", roles.toArray(new String[roles.size()]))
							.withExpiresAt(expiration)
							.sign(Algorithm.HMAC256(SecurityUtils.PRIVATE_SECRET));
		response.addHeader(SecurityUtils.JWT_HEADER, jwt);
	}
	
}
