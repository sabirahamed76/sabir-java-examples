/*
 * Placements.java
 *
 * Created on December 14, 2009, 10:47 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.utils.grandprix;


public class Placements {
	private Driver driver;

	private Team team;

	private int timeTaken;

	private boolean hasCompleted;

	//(A)Connstuctor
	public Placements(Driver d, int timeTaken, boolean hasCompleted) {
	  this.driver = d;
	  this.timeTaken = timeTaken;
	  this.hasCompleted = hasCompleted;
	  this.team = d.getTeam();//get the team from driver object
	}
	
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public boolean isHasCompleted() {
		return hasCompleted;
	}

	public void setHasCompleted(boolean hasCompleted) {
		this.hasCompleted = hasCompleted;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}

}
