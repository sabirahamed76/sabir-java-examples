/*
 * GrandPrix.java
 *
 * Created on December 14, 2009, 10:47 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.utils.grandprix;


import java.util.Date;
import java.util.Vector;

public class GrandPrix {
	private Driver drivers[];

	private Team teams[];

	private Race races[];

	public Driver[] getDrivers() {
		return drivers;
	}

	public void setDrivers(Driver[] drivers) {
		this.drivers = drivers;
	}

	public Race[] getRaces() {
		return races;
	}

	public void setRaces(Race[] races) {
		this.races = races;
	}

	public Team[] getTeams() {
		return teams;
	}

	public void setTeams(Team[] teams) {
		this.teams = teams;
	}
    
	//(C) print team points
	public void printTeamPoints() {
	     if(teams == null )
	    	 return;
	     int teamSize = teams.length;
	     //Iterate the team
	     for(int i=0; i<teamSize; i++) {
	    	 int totalTeamPoints = 0;
	    	 Team aTeam = teams[i];
	    	 System.out.println(aTeam.getName());
	    	 //iterate the race to get placement for each team
	    	 if(races != null) {
	    		 int raceSize = races.length;
	    		 for(int j=0; j<raceSize; j++) {
	    			 Race aRace = races[j];
	    			 int teamPoints = aRace.getTeamPoints(aTeam);
	    			 totalTeamPoints = totalTeamPoints + teamPoints;//accumulate team points
	    			 if(teamPoints > 0 )
	    				 System.out.println("\t"+aRace.getName() +" - "+ teamPoints + " points");
	    		 }
	    		 System.out.println("\t** TOTAL : "+totalTeamPoints +" points **");
	    	 }
	     }
     
	}
	
	public static void main(String args[]) {
		Team a = new Team("Benetton", "US");		
		Team b =  new Team("McLaren", "UK");
		Team c =  new Team("Old Bangers", "IT");
		Team d =  new Team("Ferrari", "IN");
		
		Team[] teams = {a,b,c,d};
		
		Driver d1 = new Driver("A", "US", a, 10);
		Driver d2 = new Driver("B", "US", a, 10);
		Driver d3 = new Driver("C", "US", a, 10);
		
		Driver d4 = new Driver("D", "US", b, 10);
		Driver d5 = new Driver("E", "US", b, 10);
		Driver d6 = new Driver("F", "US", b, 10);
		Driver d7 = new Driver("G", "US", b, 10);
		
		Driver d8 = new Driver("H", "US", d, 10);
		Driver d9 = new Driver("I", "US", d, 10);
		Driver d10 = new Driver("J", "US", d, 10);
		
		Driver[] drivers = {d1, d2, d3, d4, d4, d6, d7, d8, d10};
		
		Race ra = new Race("Argentina", new Date());
		Placements p2 = new Placements(d4, 10, true);
		Placements p1 = new Placements(d1, 10, true);
		Vector v1 = new Vector();
		v1.add(p1);
		v1.add(p2);
		ra.setOrderOfArrival(v1);
		
		Race rg = new Race("Germany", new Date());
		Placements p5 = new Placements(d5, 10, true);
		Placements p4 = new Placements(d2, 10, true);
		Placements p6 = new Placements(d8, 10, true);
		Vector v2 = new Vector();
		v2.add(p4);
		v2.add(p5);
		v2.add(p6);
		rg.setOrderOfArrival(v2);
		
		Race rb = new Race("Brazil", new Date());
		Placements p81 = new Placements(d6, 10, true);
		Placements p8 = new Placements(d3, 10, true);
		Vector v3 = new Vector();
		v3.add(p81);
		v3.add(p8);
		rb.setOrderOfArrival(v3);
		
		Race ri = new Race("Itally", new Date());
		Placements p9 = new Placements(d7, 10, true);
		Placements p10 = new Placements(d10, 10, true);
		Vector v4 = new Vector();
		v4.add(p9);
		v4.add(p10);
		ri.setOrderOfArrival(v4);
		
		Race[] races = {ra, rg, rb, ri};
		
		
		GrandPrix  mainApp = new GrandPrix();
		mainApp.setDrivers(drivers);
		mainApp.setRaces(races);
		mainApp.setTeams(teams);
		
		mainApp.printTeamPoints();
	}
}
