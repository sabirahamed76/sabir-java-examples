package com.home.sabir.spring.junit;

import java.util.List;

public class Student {
	
	private String id;
	private String name;
	private String description;
	private List<Course> courses;
	
	public Student(String id,String name,String description,List<Course> courses) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.courses=courses;
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public String id() {
		return this.id;
	}
	public List<Course> courses() {
		return this.courses ;
	}
    

}