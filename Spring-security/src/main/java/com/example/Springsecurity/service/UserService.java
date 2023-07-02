package com.example.Springsecurity.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Springsecurity.dto.RegistrationUser;
import com.example.Springsecurity.model.User;
import com.example.Springsecurity.repository.RoleRepository;
import com.example.Springsecurity.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Autowired
	@Lazy
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	public User findByUsername(String name) {
		return userRepository.findByUsername(name);
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User not found", username));
		}
		
		return new org.springframework.security.core.userdetails.User(
				   user.getUsername(),
				   user.getPassword(),
				   user.getRoles()
				   .stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
	}
    public void createNewUser(RegistrationUser registrationUser) {
		
		User user = new User();
		user.setUsername(registrationUser.getUsername());
		user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
		user.setEmail(registrationUser.getEmail());
		
		user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
		userRepository.save(user);
	}
	
}
