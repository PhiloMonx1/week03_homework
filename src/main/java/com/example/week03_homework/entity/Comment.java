package com.example.week03_homework.entity;

import com.example.week03_homework.dto.CommentRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "blog_id")
	@JsonBackReference
	private Blog blog;

	@ManyToOne
	@JoinColumn(name = "username")
	@JsonBackReference
	private Users users;

	public Comment(CommentRequestDto commentRequestDto, Blog blog) {
		this.name = "익명";
		this.title = commentRequestDto.getTitle();
		this.content = commentRequestDto.getContent();
		this.blog = blog;
	}

	public void updata(CommentRequestDto commentRequestDto) {
		this.title = commentRequestDto.getTitle();
		this.content = commentRequestDto.getContent();
	}
}
