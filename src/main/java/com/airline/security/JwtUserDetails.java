package com.airline.security;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.airline.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String username;
	
	@JsonIgnore
	private String password;
	

	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtUserDetails() {
		
	}

	public JwtUserDetails(Long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	
	 public static JwtUserDetails build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new JwtUserDetails(
				user.getId(), 
				user.getUsername(), 
				user.getPassword(), 
				authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
