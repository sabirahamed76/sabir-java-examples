package com.home.sabir.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class StudentBusinessService {

    private final StudentDataService dataService;

    public StudentBusinessService(StudentDataService dataService) {
        super();
        this.dataService = dataService;
    }

    public int findTheGreatestFromAllData() {
        int[] data = dataService.retrieveAllData();
        int greatest = Integer.MIN_VALUE;

        for (int value : data) {
            if (value > greatest) {
                greatest = value;
            }
        }
        return greatest;
    }
}