package com.inventory.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.inventory.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@PostMapping("/login")
	public String adminLogin(@RequestParam("username") String username,
	                         @RequestParam("password") String password,
	                         RedirectAttributes redirectAttributes){
	    
	    if (userService.userLogin(username, password) != null) {
	        return "redirect:/dashboard";
	    } else {
	        redirectAttributes.addFlashAttribute("error", "Invalid username or password");
	        return "redirect:/login-form?error=true"; // Redirect to login form
	    }
	}

}
