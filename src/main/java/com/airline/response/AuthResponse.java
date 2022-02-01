package com.airline.response;

import java.util.List;

public class AuthResponse {

	String message = "Bearer ";
	Long userId;
	private List<String> roles;
	
	public AuthResponse() {
		
	}
	
	public AuthResponse(String message, Long userId, String password, String username, List<String> roles) {
		this.message = message;
		this.userId = userId;
	    this.setRoles(roles);
	}


	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}