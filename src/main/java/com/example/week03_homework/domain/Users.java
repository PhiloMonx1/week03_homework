package com.example.week03_homework.domain;

import com.example.week03_homework.dto.UserRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Users {

	@Id// 유저네임으로 FK주고 DB저장 되는 이름도 username로 바꿈
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@OneToMany()
	@JsonManagedReference
	private List<Blog> blogList;

	@OneToMany()
	@JsonManagedReference
	private List<Comment> commentList;

	public Users(UserRequestDto userRequestDto) {
		this.username = userRequestDto.getUsername();
		this.password = userRequestDto.getPassword();
	}
}
