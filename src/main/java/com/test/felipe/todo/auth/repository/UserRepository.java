package com.test.felipe.todo.auth.repository;

import com.test.felipe.todo.auth.login.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}