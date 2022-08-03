package com.example.week03_homework.contorller;

import com.example.week03_homework.entity.Blog;
import com.example.week03_homework.dto.BlogRequestDto;
import com.example.week03_homework.dto.BlogResponseDto;
import com.example.week03_homework.entity.UserRoleEnum;
import com.example.week03_homework.entity.Users;
import com.example.week03_homework.repository.UserRepository;
import com.example.week03_homework.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
	private final BlogService blogService;
	private final UserRepository userRepository;// 로그인 구현 전 테이블 관계 매핑 테스트용

	@Autowired
	public BlogController(BlogService blogService, UserRepository userRepository){
		this.blogService = blogService;
		this.userRepository = userRepository;
	}


	@GetMapping("/api/post")
	public List<BlogResponseDto> getBoards() {
		return blogService.readList();
	}

	@GetMapping("/api/post/{id}")
	public Blog getBlogByid(@PathVariable Long id) {
		return blogService.findById(id);
	}

	@PostMapping("/api/auth/post")
	public Blog createBlog(@RequestBody BlogRequestDto blogRequestDto){
		Users user = new Users("익명", "1234", UserRoleEnum.USER);
		userRepository.save(user);
		return blogService.creatPost(blogRequestDto, user);
	}

	@PutMapping("/api/auth/post/{id}")
	public String updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto blogRequestDto){
		return blogService.update(id, blogRequestDto);
	}

	@DeleteMapping("/api/auth/post/{id}")
	public String deleteBlog(@PathVariable Long id){
		return blogService.delete(id);
	}


}//💭
