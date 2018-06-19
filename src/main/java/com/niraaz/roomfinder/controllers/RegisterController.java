package com.niraaz.roomfinder.controllers;

import com.niraaz.roomfinder.entities.User;
import com.niraaz.roomfinder.services.RoomService;
import com.niraaz.roomfinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String registerForm(Model model){
		model.addAttribute( "user", new User( ) );
		return "views/registerForm";
	}

	@PostMapping("/register")
	public String registerUser( @Valid User user, BindingResult bindingResult, Model model ) {
		if(bindingResult.hasErrors()) {
			return "views/registerForm";
		}

		if(userService.isUserPresent(user.getUsername(), user.getEmail())){
			model.addAttribute( "exist", true );
			return "views/registerForm";
		}

		userService.createUser( user );
		return "views/success";
	}
}
