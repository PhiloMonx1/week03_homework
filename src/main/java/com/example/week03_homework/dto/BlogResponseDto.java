package com.example.week03_homework.dto;

import com.example.week03_homework.domain.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BlogResponseDto {
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private Long id;
	private String name;
	private String title;

	public BlogResponseDto(Blog blog) {
		this.createdAt = blog.getCreatedAt();
		this.modifiedAt = blog.getModifiedAt();
		this.id = blog.getId();
		this.name = blog.getName();
		this.title = blog.getTitle();
	}
}
