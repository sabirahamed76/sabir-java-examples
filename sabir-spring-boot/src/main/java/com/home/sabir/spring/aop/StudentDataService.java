package com.home.sabir.spring.aop;


import org.springframework.stereotype.Repository;

@Repository
public class StudentDataService {
    public int[] retrieveAllData() {
        // Some dummy data
        // Actually this should talk to some database to get all the data
        return new int[]{1, 2, 3, 4, 5};
    }
}