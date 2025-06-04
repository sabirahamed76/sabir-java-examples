package com.home.sabir.spring.junit;

import java.util.List;

public class Course {
	
	private java.lang.String id;
	private java.lang.String name;
	private java.lang.String description;
	private List<String> steps;
	
	public Course(String id,String name,String description,List<String> steps) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.steps=steps;
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getDescription() {
		return description;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	public List<String> getSteps() {
		return steps;
	}
	public void setSteps(List<String> steps) {
		this.steps = steps;
	}
	public String id() {
		return this.id;
	}
	  
}