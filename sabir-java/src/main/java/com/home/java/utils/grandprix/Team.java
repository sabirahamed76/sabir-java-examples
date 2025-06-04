/*
 * Team.java
 *
 * Created on December 14, 2009, 10:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.utils.grandprix;

public class Team {
	private String name;
	private String country;
  
	public Team(String name, String country) {
		this.name = name;
		this.country = country;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
  
  
}
