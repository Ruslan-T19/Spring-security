package com.example.Springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/entrance")
public class entranceControler {
	

	@GetMapping("/auth")
	public String auth() {
		return "login";
	}
}
