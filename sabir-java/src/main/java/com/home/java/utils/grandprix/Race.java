/*
 * Race.java
 *
 * Created on December 14, 2009, 10:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.utils.grandprix;

import java.util.Date;
import java.util.Vector;

public class Race {
	private String name;

	private Date date;

	private Vector orderOfArrival;// for placements

	private static int points[] = { 9, 6, 4, 3, 2, 1 };

	public Race(String name, Date date) {
	  this.name = name;
	  this.date = date;
	}
	
	public static int[] getPoints() {
		return points;
	}

	public static void setPoints(int[] points) {
		Race.points = points;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector getOrderOfArrival() {
		return orderOfArrival;
	}

	public void setOrderOfArrival(Vector orderOfArrival) {
		this.orderOfArrival = orderOfArrival;
	}

	//(B) print Team points
	public int getTeamPoints(Team t) {
		int teamPoints =0;
		if(orderOfArrival != null) {
			int size = orderOfArrival.size();
			for(int i=0; i<size; i++) {
				Placements aPlace = (Placements)orderOfArrival.get(i);
				if(aPlace.getTeam().getName().equals(t.getName())) {
					//System.out.println("Inside");
					if(i<=5) { //from 1 to 6'th position
						teamPoints = teamPoints + points[i];
					} else {
						//no points given
					}
				}
			}
		}
		return teamPoints;
	}
}
