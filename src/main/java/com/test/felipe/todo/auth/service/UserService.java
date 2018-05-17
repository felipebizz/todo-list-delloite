package com.test.felipe.todo.auth.service;

import com.test.felipe.todo.auth.login.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
