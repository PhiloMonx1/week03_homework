package com.example.week03_homework.repository;

import com.example.week03_homework.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users,String> {

}
