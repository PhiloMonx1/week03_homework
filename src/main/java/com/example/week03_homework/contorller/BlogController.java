package com.example.week03_homework.contorller;

import com.example.week03_homework.entity.Blog;
import com.example.week03_homework.dto.BlogRequestDto;
import com.example.week03_homework.dto.BlogResponseDto;
import com.example.week03_homework.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
	private final BlogService blogService;

	@Autowired
	public BlogController(BlogService blogService){
		this.blogService = blogService;
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
		return blogService.save(blogRequestDto);
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
