package com.home.sabir.spring.aop;

import org.springframework.stereotype.Repository;

@Repository
public class Dao2 {

    public String retrieveSomething() {
        return "Dao2";
    }

}