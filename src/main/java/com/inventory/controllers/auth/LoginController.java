package com.inventory.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String adminLogin() {
		return "adminlogin";
	}
	
	
}
