package com.test.felipe.todo.auth.repository;

import com.test.felipe.todo.auth.login.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
