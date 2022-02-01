package com.airline.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.model.ERole;
import com.airline.model.Role;
import com.airline.model.User;
import com.airline.repository.RoleRepository;
import com.airline.repository.UserRepository;
import com.airline.request.UserRequest;
import com.airline.response.AuthResponse;
import com.airline.security.JwtTokenProvider;
import com.airline.security.JwtUserDetails;
import com.airline.service.UserService;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenProvider jwtTokenProvider;
	
	private UserService userService;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserRequest loginRequest) {
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		
		JwtUserDetails user = (JwtUserDetails) auth.getPrincipal();

		List<String> roles = user.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
	
		return ResponseEntity.ok(new AuthResponse(jwtToken, user.getId() , user.getPassword(),user.getUsername(), roles));
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest) {
		AuthResponse authResponse = new AuthResponse();
		if(userService.getOneUserByUsername(registerRequest.getUsername()) != null) { 
			authResponse.setMessage("Username already in use");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}

		User user = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
		
		Set<String> strRoles = registerRequest.getRoles();
		
		Set<Role> roles = new HashSet<>();
		
		if (strRoles == null) {
			Role userRole = roleRepo.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "editor":
					Role modRole = roleRepo.findByName(ERole.ROLE_EDITOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepo.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		authResponse.setMessage("User Succesfully registared");
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}
}