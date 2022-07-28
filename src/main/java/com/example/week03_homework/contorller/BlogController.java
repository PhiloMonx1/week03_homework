package com.example.week03_homework.contorller;

import com.example.week03_homework.domain.Blog;
import com.example.week03_homework.dto.BlogPwDto;
import com.example.week03_homework.dto.BlogRequestDto;
import com.example.week03_homework.repository.BlogRepository;
import com.example.week03_homework.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BlogController {
	private final BlogRepository blogRepository;
	private final BlogService blogService;


	@GetMapping("/api/post")
	public List<Blog> getBlogsAll(){
		LocalDateTime start = LocalDateTime.now().minusDays(1);
		LocalDateTime end = LocalDateTime.now();
		return blogRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
	}
	@GetMapping("/api/post/{id}")
	public Optional<Blog> getBlogByid(@PathVariable Long id){
		  blogRepository.findById(id);
		 return blogRepository.findById(id);
	}

	@PostMapping("/api/post")
	public Blog createBlog(@RequestBody BlogRequestDto requestDto){
		Blog blog = new Blog(requestDto);
		return blogRepository.save(blog);
	}
	@PostMapping("/api/post/{id}")//
	public String checkPw(@PathVariable Long id, @RequestBody BlogPwDto pwDto){
		return blogService.check(id, pwDto);
	}

	@PutMapping("/api/post/{id}")// *** 패스워드 일치 확인 여기서? 아니면 Service에서?
	public String updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto){
		return blogService.update(id, requestDto);
	}

	@DeleteMapping("/api/post/{id}")// ***패스워드 일치 후 삭제 하도록 하는거 필요
	public String deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto pwDto){
		return blogService.delete(id, pwDto);
	}


}//💭
