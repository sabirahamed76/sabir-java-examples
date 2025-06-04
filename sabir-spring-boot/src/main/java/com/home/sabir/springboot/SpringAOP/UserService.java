package com.home.sabir.springboot.SpringAOP;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String createUser() {
        System.out.println("User created!");
        return "Success";
    }

    public void throwError() {
        throw new RuntimeException("Something went wrong!");
    }
}
