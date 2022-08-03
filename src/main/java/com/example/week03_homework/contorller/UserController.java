package com.example.week03_homework.contorller;

import com.example.week03_homework.dto.SignupRequestDto;
import com.example.week03_homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@PostMapping("/api/user/signup")
	public String registerUser(@RequestBody SignupRequestDto signupRequestDto){
		return userService.registerUser(signupRequestDto);
	}
}
