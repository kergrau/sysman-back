package com.sysman.tecnica.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.sysman.tecnica.entities.Role;
import com.sysman.tecnica.entities.User;
import com.sysman.tecnica.enums.ECustomError;
import com.sysman.tecnica.enums.ERole;
import com.sysman.tecnica.exceptions.CustomException;
import com.sysman.tecnica.models.LoginRequestDto;
import com.sysman.tecnica.models.SignUpDto;
import com.sysman.tecnica.models.UserInfoResponseDto;
import com.sysman.tecnica.repositories.RoleRepository;
import com.sysman.tecnica.repositories.UserRepository;
import com.sysman.tecnica.security.jwt.JwtUtils;
import com.sysman.tecnica.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).toList();

		String token = jwtUtils.generateToken(userDetails.getUsername(), roles);

		return new ResponseEntity<>(new UserInfoResponseDto(userDetails.getUsername(), userDetails.getEmail(), token),
				HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpRequest) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new CustomException(ECustomError.USERNAME_TAKEN, HttpStatus.CONFLICT);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new CustomException(ECustomError.EMAIL_TAKEN, HttpStatus.CONFLICT);
		}

		// Create new user's account
		User user = new User();
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setEmail(signUpRequest.getEmail());
		user.setUsername(signUpRequest.getUsername());

		Set<Role> roles = new HashSet<>();

		Role userRole = roleRepository.findByName(ERole.ROLE_USER.getRoleName())
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);

		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
