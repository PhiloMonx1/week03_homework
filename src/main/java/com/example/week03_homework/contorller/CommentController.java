package com.example.week03_homework.contorller;

import com.example.week03_homework.domain.Comment;
import com.example.week03_homework.dto.CommentRequestDto;
import com.example.week03_homework.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService){
		this.commentService = commentService;
	}

	@GetMapping("/api/comment/{blogId}")
	public List<Comment> getComments(@PathVariable Long blogId){
		return commentService.getComments(blogId);
	}

	@PostMapping("/api/auth/comment/{blogId}")
	public Comment createComment(@PathVariable Long blogId, @RequestBody CommentRequestDto commentRequestDto){
		return commentService.createComment(blogId, commentRequestDto);
	}

	@PutMapping("/api/auth/comment/{blogId}/{cmtId}")
	public String updateComment(@PathVariable Long blogId, @PathVariable Long cmtId, @RequestBody CommentRequestDto commentRequestDto){
		return commentService.updateComment(blogId, cmtId, commentRequestDto);
	}

	@DeleteMapping("/api/auth/comment/{blogId}/{cmtId}")
		public String deteleComment(@PathVariable Long blogId, @PathVariable Long cmtId){
		return commentService.deteleComment(blogId, cmtId);
	}
}