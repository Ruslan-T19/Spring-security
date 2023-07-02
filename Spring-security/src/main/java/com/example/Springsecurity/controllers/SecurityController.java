package com.example.Springsecurity.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Springsecurity.model.User;
import com.example.Springsecurity.service.UserService;


@Controller
@RequestMapping
public class SecurityController {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/page")
	public String Page() {
		return "page";
	}
	
	@GetMapping("/user")
	public String UserAuth() {
		return "user";
	}
	
	@GetMapping("/admin")
	public String AdminAuth() {
		return "admin";
	}
	
	@GetMapping("/info")
	public String Info(Model model, Principal principal) {
		
		User user =  userService.findByUsername(principal.getName());
		
		System.out.println(user.getEmail());
		
		model.addAttribute("name", user.getEmail());
		
		return "information";
	}
}
