package com.example.week03_homework.entity;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY로 바꿔주면 테이블마다 ID 따로 쌓인다.
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;


//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // fetch = FetchType.EAGER = commentList쿼리 묶어서 보내줌
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private List<Comment> commentList;

	@ManyToOne()
	@JoinColumn(name = "username")
	@JsonBackReference
	private Users users;

	public Blog(BlogRequestDto requestDto, Users users){
		this.name = "테스트";
//		this.name = users.getUsername();
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
		this.users = users;
	}

	public void update(BlogRequestDto requestDto){
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
	}

	public void addComment(Comment comment){
		this.commentList.add(comment);
	}
	public void removeComment(Comment comment){
		this.commentList.remove(comment);
	}
}

