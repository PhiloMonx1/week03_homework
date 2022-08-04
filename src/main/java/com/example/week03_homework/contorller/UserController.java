package com.example.week03_homework.contorller;

import com.example.week03_homework.dto.SignupRequestDto;
import com.example.week03_homework.entity.Users;
import com.example.week03_homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@GetMapping("/api/user/{username}")
	public Users findUser(@PathVariable String username){
		return userService.findUser(username);
	}

	@PostMapping("/api/user/signup")
	public String registerUser(@RequestBody SignupRequestDto signupRequestDto){
		return userService.registerUser(signupRequestDto);
	}
}
