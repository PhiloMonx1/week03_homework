package com.example.week03_homework.service;

import com.example.week03_homework.entity.UserRoleEnum;
import com.example.week03_homework.entity.Users;
import com.example.week03_homework.dto.SignupRequestDto;
import com.example.week03_homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	public String registerUser(SignupRequestDto signupRequestDto) {
		String username = signupRequestDto.getUsername();
		String password = signupRequestDto.getPassword();
		String password2 = signupRequestDto.getPassword2();
		String passwordCry = passwordEncoder.encode(password);
		UserRoleEnum role = UserRoleEnum.USER;

		if(password.equals(password2)){
			Optional<Users> User = userRepository.findById(username);
			if(User.isEmpty()){
				Users newUser = new Users(username, passwordCry, role);
				userRepository.save(newUser);
				return "회원가입을 축하합니다.";
			}else return "이미 존재하는 아이디입니다.";
		}else return "비밀번호가 맞지 않습니다.";
	}

	public Users findUser(String username) {
		return userRepository.findById(username)
				.orElseThrow(() -> new NullPointerException("존재하지 않는 사용자입니다."));
	}
}