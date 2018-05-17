package com.test.felipe.todo.auth.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);
}
