package com.example.week03_homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlogRequestDto {
	private final String name;
	private final String title;
	private final String content;
	private final String password;
}