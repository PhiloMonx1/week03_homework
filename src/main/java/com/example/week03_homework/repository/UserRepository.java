package com.example.week03_homework.repository;

import com.example.week03_homework.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,String> {
	Optional<Users> findByUsername(String username);
}
