package com.sportyshoes.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		//
		if (!(request.getRequestURI().startsWith("/login"))) {

			final String jwtToken = request.getHeader(SecurityUtils.JWT_HEADER);
			if (jwtToken == null || !jwtToken.startsWith(SecurityUtils.TOKEN_PREFIX))
				throw new RuntimeException("NOT_AUTHORIZED");

			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityUtils.PRIVATE_SECRET)).build();
			final String jwt = jwtToken.substring(SecurityUtils.TOKEN_PREFIX.length());

			DecodedJWT decodeJWT = verifier.verify(JWT.decode(jwt));

			String username = decodeJWT.getSubject();
			List<String> roles = decodeJWT.getClaims().get("roles").asList(String.class);
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(name -> {
				authorities.add(new SimpleGrantedAuthority(name));
			});
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(token);
			filterChain.doFilter(request, response);
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
