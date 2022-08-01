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


//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // fetch = FetchType.EAGER = commentList쿼리 묶어서 보내줌
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference
	private List<Comment> commentList;

	@ManyToOne()
	@JoinColumn(name = "username")
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

	public void update(BlogRequestDto requestDto){
		this.name = requestDto.getUsername();
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

