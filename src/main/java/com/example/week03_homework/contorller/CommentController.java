package com.example.week03_homework.contorller;

import com.example.week03_homework.domain.Comment;
import com.example.week03_homework.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;

public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService){
		this.commentService = commentService;
	}

//	@PostMapping("/api/auth/post")
//	public Comment createComment(){
//
//	}
}