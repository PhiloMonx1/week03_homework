package com.example.week03_homework.repository;

import com.example.week03_homework.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByBlog_Id(Long blogId);
	Comment findByBlog_IdAndId(Long blogId, Long id);
}
