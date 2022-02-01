package com.airline.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.airline.service.UserServiceImpl;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwtToken = extractJwtFromRequest(request);
			
			if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
				
				String username = jwtTokenProvider.getUsernameFromJwt(jwtToken);
				
				UserDetails user = userServiceImpl.loadUserByUsername(username);
				
				if(user != null) {
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
			
		} catch (Exception e) {
			log.error("Cannot set user authentication: {}", e);
		}
		filterChain.doFilter(request, response);
		
	}

	
	
	private String extractJwtFromRequest(HttpServletRequest request) {

		String bearer = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
			
			return bearer.substring("Bearer".length() + 1);
		
		return null;
	}

	
}
