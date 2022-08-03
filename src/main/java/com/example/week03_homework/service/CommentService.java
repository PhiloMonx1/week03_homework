package com.example.week03_homework.service;

import com.example.week03_homework.dto.CommentRequestDto;
import com.example.week03_homework.entity.Blog;
import com.example.week03_homework.entity.Comment;
import com.example.week03_homework.entity.UserRoleEnum;
import com.example.week03_homework.entity.Users;
import com.example.week03_homework.repository.BlogRepository;
import com.example.week03_homework.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final BlogRepository blogRepository;

	public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
		this.commentRepository = commentRepository;
		this.blogRepository = blogRepository;
	}


	@Transactional
	public Comment createComment(Long blogId, CommentRequestDto commentRequestDto) {
		Blog blogById = blogRepository.findById(blogId)
				.orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

		Users user = new Users("신짱구", "1234", UserRoleEnum.USER);

//		Comment comment = new Comment(commentRequestDto, blogById);
		List<Users> usersList = null;
		usersList.add(user);

		System.out.println(usersList.get(0));

		Comment comment = new Comment(commentRequestDto);

//		blogById.addComment(comment);
		user.addComment(comment);

		return comment;
	}

	public List<Comment> getComments(Long blogId) {
		return commentRepository.findAllByBlog_Id(blogId);
	}

	@Transactional
	public String updateComment(Long blogId, Long cmtId, CommentRequestDto commentRequestDto) {
		Comment comment = commentRepository.findByBlog_IdAndId(blogId, cmtId);
		comment.updata(commentRequestDto);
		return "수정완료";
	}

	@Transactional
	public String deteleComment(Long blogId, Long cmtId) {
		Blog blogById = blogRepository.findById(blogId)
				.orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
		Comment comment = commentRepository.findById(cmtId)
				.orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
		blogById.removeComment(comment);
//		commentRepository.deleteById(cmtId); == 이걸로는 삭제 안된다! 블로그에서 지워야 해!!
		return "삭제완료" + comment.getId(); 
	}
}
