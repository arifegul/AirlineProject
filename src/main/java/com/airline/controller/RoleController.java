package com.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airline.model.Role;
import com.airline.service.RoleService;

@RestController
public class RoleController {
	
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping({"/createNewRole"})
	public Role createNewRole(@RequestBody Role role) {
		return roleService.createNewRole(role);
		
	}
}
