package com.home.sabir.spring.aop;

import org.springframework.stereotype.Repository;

@Repository
public class Dao1 {

    public String retrieveSomething() {
        return "Dao1";
    }

}
