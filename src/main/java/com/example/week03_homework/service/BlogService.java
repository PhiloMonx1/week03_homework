package com.example.week03_homework.service;

import com.example.week03_homework.dto.BlogRequestDto;
import com.example.week03_homework.dto.BlogResponseDto;
import com.example.week03_homework.entity.Blog;
import com.example.week03_homework.entity.Users;
import com.example.week03_homework.repository.BlogRepository;
import com.example.week03_homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
	private final BlogRepository blogRepository;
	private final UserRepository userRepository;

	@Autowired
	public BlogService (BlogRepository blogRepository, UserRepository userRepository){
		this.blogRepository = blogRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public List<BlogResponseDto> readList() {
		List<Blog> blogList = blogRepository.findAllByOrderByModifiedAtDesc(); //블로그만 들어올 수 있는 방 = (DB에서 나온)블로그들이 들어옴 
		List<BlogResponseDto> blogResponseDtoList = new ArrayList<>(); // 블로그 리스폰 DTO만 들어올 수 있는 방 만듬
		for (Blog blog : blogList) {
			blogResponseDtoList.add(new BlogResponseDto(blog));
		}
		return blogResponseDtoList;
	}

	public Blog findById(Long id) {

		return blogRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
	}

	@Transactional
	public Blog creatPost(BlogRequestDto requestDto, Users users) {
		Users byUsername = userRepository.findByUsername(users.getUsername())
				.orElseThrow(()-> new IllegalArgumentException("잘못된 사용자입니다. 다시 로그인 후 시도해주세요."));

		Blog blog = new Blog(requestDto, byUsername);
		blogRepository.save(blog);

		System.out.println("=======서비스2========");
		System.out.println(blog.getId());
		System.out.println(blog.getName());
		System.out.println(blog.getTitle());
		System.out.println(blog.getContent());
		System.out.println("=======서비스2========");

//		byUsername.addBlog(blog);
//
		return blog;
	}

	@Transactional
	public String update(Long id, BlogRequestDto requestDto) {
		Blog blogById = blogRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
		blogById.update(requestDto);
		return "수정 완료";
	}

	public String delete(Long id) {
		Blog blogById = blogRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
		blogRepository.deleteById(id);
		return "삭제 완료 : " + blogById.getId();
	}
}