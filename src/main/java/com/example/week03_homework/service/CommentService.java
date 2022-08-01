package com.example.week03_homework.service;

import com.example.week03_homework.repository.CommentRepository;

public class CommentService {

	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}


}
