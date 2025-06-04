package com.home.sabir.microservices.SpringCloudEmployeeService.model;

public record Employee(Long id, Long departmentId, String name, int age, String position) {
}
