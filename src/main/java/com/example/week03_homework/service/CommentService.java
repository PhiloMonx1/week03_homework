package com.example.week03_homework.service;

import com.example.week03_homework.dto.CommentRequestDto;
import com.example.week03_homework.entity.Blog;
import com.example.week03_homework.entity.Comment;
import com.example.week03_homework.entity.Users;
import com.example.week03_homework.repository.BlogRepository;
import com.example.week03_homework.repository.CommentRepository;
import com.example.week03_homework.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final BlogRepository blogRepository;

	private final UserRepository userRepository;

	public CommentService(CommentRepository commentRepository, BlogRepository blogRepository, UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.blogRepository = blogRepository;
		this.userRepository = userRepository;
	}

	@Transactional // 영속성
	public Comment createComment(Long blogId, CommentRequestDto commentRequestDto) {
		Blog blogById = blogRepository.findById(blogId)
				.orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

    Users userById = userRepository.findById(commentRequestDto.getUsername())
		    .orElseThrow(() -> new NullPointerException("잘못된 사용자입니다. 다시 로그인 후 시도해주세요."));

		Comment comment = new Comment(commentRequestDto, blogById, userById);
		blogById.addComment(comment);
//		commentRepository.save(comment);

//		userById.addComment(comment); // 양방향 관계에서는 안줘도 되는듯하다 -> 백기선 님은 주라고 했던 것 같은데 주고 안주고 차이 알아낼것!

		return comment;
	}

	public List<Comment> getComments(Long blogId) {
		return commentRepository.findAllByBlog_Id(blogId);
	}

	@Transactional
	public String updateComment(Long cmtId, CommentRequestDto commentRequestDto) {
		Comment comment = commentRepository.findById(cmtId)
				.orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
		comment.updata(commentRequestDto);
		return "수정완료";
	}

//	@Transactional
	public String deteleComment(Long cmtId) {
		Comment comment = commentRepository.findById(cmtId)
				.orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

		Blog blogById = blogRepository.findById(comment.getBlog().getId())
				.orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

		blogById.removeComment(comment);

//		Users userbyId = userRepository.findById(comment.getUsers().getUsername())
//				.orElseThrow(() -> new NullPointerException("잘못된 사용자입니다. 다시 로그인 후 시도해주세요."));

//		userbyId.removeComment(comment); //양방향 관계에서는 안줘도 되는듯 하다 -> 백기선 님은 주라고 했던 것 같은데 주고 안주고 차이 알아낼것!

		commentRepository.deleteById(cmtId);

		return "삭제완료" + comment.getId();
//		commentRepository.deleteById(cmtId); == 이걸로는 삭제 안된다! 블로그에서 지워야 해!!
	}
}
