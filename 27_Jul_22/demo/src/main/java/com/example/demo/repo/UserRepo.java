package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<List<User>> findByNickName(String nickName);
    Optional<List<User>> findByNickNameContainingIgnoreCase(String nickName);
    Optional<List<User>> findByAge(int age);
}
