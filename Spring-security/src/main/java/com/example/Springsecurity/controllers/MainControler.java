package com.example.Springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControler {

	@GetMapping("/main")
	public String main() {
		return "index";
	}
}
