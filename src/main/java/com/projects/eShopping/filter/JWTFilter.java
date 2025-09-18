package com.projects.eShopping.filter;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projects.eShopping.model.User;
import com.projects.eShopping.repo.UserRepo;
import com.projects.eShopping.service.JWTService;
import com.projects.eShopping.service.UserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter{
	
	private JWTService jwtService;
	private ApplicationContext springContext;
	

	public JWTFilter(JWTService jwtService, ApplicationContext springContext) {
		super();
		this.jwtService = jwtService;
		this.springContext = springContext;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		
		String username = null, token = null;
		User user = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
		}
		
		if(username != null)
			user = springContext.getBean(UserRepo.class).findByUsername(username);
		
		if(user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = springContext.getBean(UserDetailsService.class).loadUserByUsername(username);
			
			if(jwtService.validateToken(token)) {
				UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				upaToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upaToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
