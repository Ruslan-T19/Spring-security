package com.example.Springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Springsecurity.dto.RegistrationUser;
import com.example.Springsecurity.service.UserService;

@Controller
@RequestMapping("/registr")
public class RegistrationControler {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/new")
	public String newUser(@ModelAttribute("registrationUser") RegistrationUser registrationUser) {
		return "registration";
	}
	
	@PostMapping
	public String createUser(@ModelAttribute("person") RegistrationUser registrationUser) {
		userService.createNewUser(registrationUser);
	    return "redirect:/main";
	}
}
