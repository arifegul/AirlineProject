package com.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.model.Role;
import com.airline.repository.RoleRepository;

@Service
public class RoleService {

	
	@Autowired
	private RoleRepository roleRepo;
	
	
	public Role createNewRole(Role role) {
		
		return roleRepo.save(role);
	}
}
