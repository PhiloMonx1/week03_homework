package com.example.week03_homework.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment {
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
	@JoinColumn(name = "users_id")
	@JsonBackReference
	private Users users;
}
