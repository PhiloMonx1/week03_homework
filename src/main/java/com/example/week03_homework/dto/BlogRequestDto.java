package com.example.week03_homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlogRequestDto {
	private String name;
	private String title;
	private String content;
	private String password;
}