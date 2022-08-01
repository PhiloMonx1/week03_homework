package com.example.week03_homework.domain;

import com.example.week03_homework.dto.BlogRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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


	@OneToMany(cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Comment> commentList;

	@ManyToOne
	@JoinColumn(name = "users_id")
	@JsonBackReference
	private Users users;

	public Blog(String name, String title, String content) {
		this.name = name;
		this.title = title;
		this.content = content;
	}

	public Blog(BlogRequestDto requestDto){
		this.name = requestDto.getUsername();
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
	}

	public void update(BlogRequestDto requestDto){ // ***비밀번호 동일할 때 조건 추가 필요
		this.name = requestDto.getUsername();
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
	}
}

