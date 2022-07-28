package com.example.week03_homework.domain;

import com.example.week03_homework.dto.BlogRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Blog extends Timestamped{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	@JsonIgnore
	@Column(nullable = false)
	private String password;

	public Blog(String name, String title, String content, String password) {
		this.name = name;
		this.title = title;
		this.content = content;
		this.password = password;
	}

	public Blog(BlogRequestDto requestDto){
		this.name = requestDto.getName();
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		this.password = requestDto.getPassword();
	}

	public void update(BlogRequestDto requestDto){ // ***비밀번호 동일할 때 조건 추가 필요
		this.name = requestDto.getName();
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		this.password = requestDto.getPassword();
	}
}

