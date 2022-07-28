package com.example.week03_homework.service;

import com.example.week03_homework.domain.Blog;
import com.example.week03_homework.dto.BlogPwDto;
import com.example.week03_homework.dto.BlogRequestDto;
import com.example.week03_homework.dto.BlogResponseDto;
import com.example.week03_homework.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
	private final BlogRepository blogRepository;

	@Transactional
	public List<BlogResponseDto> readList(){
		LocalDateTime start = LocalDateTime.now().minusDays(1);
		LocalDateTime end = LocalDateTime.now();
		List<Blog> blogList = blogRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(start, end);
		List<BlogResponseDto> blogResponseDtoList = new ArrayList<>();
		for(Blog blog : blogList){
			blogResponseDtoList.add(new BlogResponseDto(blog));
		}
		return blogResponseDtoList;
	}
	@Transactional
	public String  update(Long id, BlogRequestDto requestDto){ //***패스워드 일치 조건문 추가 필요
		Blog blogById = blogRepository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
		if(blogById.getPassword().equals(requestDto.getPassword())){
			blogById.update(requestDto);
			return "수정 완료 : " + blogById.getId();
		}else return "비밀번호가 틀렸습니다.";
	}
	public String check(Long id, BlogPwDto pwDto){ //***패스워드 일치 조건문 추가 필요
		Blog blogById = blogRepository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
		if (blogById.getPassword().equals(pwDto.getPassword())){
			return "비밀번호 일치";
		}else return "비밀번호 불일치";
	}

	public String delete(Long id, BlogRequestDto pwDto){
		Blog blogById = blogRepository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
		if(blogById.getPassword().equals(pwDto.getPassword())){
			blogRepository.deleteById(id);
			return "삭제 완료 : " + blogById.getId();
		}else return "비밀번호가 틀렸습니다.";
	}
}