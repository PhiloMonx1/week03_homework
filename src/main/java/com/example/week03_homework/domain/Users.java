package com.example.week03_homework.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@OneToMany(cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Blog> blogList;

	@OneToMany(cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Comment> commentList;
}
