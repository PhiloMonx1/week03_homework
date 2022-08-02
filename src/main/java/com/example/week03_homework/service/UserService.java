package com.example.week03_homework.service;

import com.example.week03_homework.entity.Users;
import com.example.week03_homework.dto.UserRequestDto;
import com.example.week03_homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	public String registerUser(UserRequestDto userRequestDto) {
		String username = userRequestDto.getUsername();
		String password = userRequestDto.getPassword();
		String password2 = userRequestDto.getPassword2();

		if(password.equals(password2)){
			Optional<Users> User = userRepository.findByUsername(username);
			if(User.isEmpty()){
				Users newUser = new Users(userRequestDto);
				userRepository.save(newUser);
				return "회원가입을 축하합니다.";
			}else return "이미 존재하는 아이디입니다.";
		}else return "비밀번호가 맞지 않습니다.";
	}
}