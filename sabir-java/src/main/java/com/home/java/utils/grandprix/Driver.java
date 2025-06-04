/*
 * Driver.java
 *
 * Created on December 14, 2009, 10:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.utils.grandprix;

public class Driver {
	private String name;

	private String country;

	private Team team;

	private int number;// number on the car

	public Driver(String name, String country, Team team, int number) {
		this.name = name;
		this.country = country;
		this.team = team;
		this.number = number;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
