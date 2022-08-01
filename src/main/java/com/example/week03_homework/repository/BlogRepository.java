package com.example.week03_homework.repository;

import com.example.week03_homework.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	List<Blog> findAllByOrderByModifiedAtDesc();
}
