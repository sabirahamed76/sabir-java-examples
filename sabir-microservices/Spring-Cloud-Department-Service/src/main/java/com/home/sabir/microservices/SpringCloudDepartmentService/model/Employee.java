package com.home.sabir.microservices.SpringCloudDepartmentService.model;

public record Employee(Long id, Long departmentId, String name, int age, String position) {
}
