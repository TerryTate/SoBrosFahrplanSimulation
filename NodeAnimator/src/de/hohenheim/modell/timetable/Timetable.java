package de.hohenheim.modell.timetable;

import java.util.ArrayList;

public class Timetable {
	
	private int id;
	private String name;
	private ArrayList<String> drivingdays;
	private int startstation;
	private int endstation;
	ArrayList<Integer> middlestations; 
	private int startHouer;
	private	int startMinutes;
	private int visits;
	
	
	public ArrayList<Integer> getMiddlestations() {
		return middlestations;
	}


	public void setMiddlestations(ArrayList<Integer> middlestations) {
		this.middlestations = middlestations;
	}


	public int getStartHouer() {
		return startHouer;
	}


	public void setStartHouer(int startHouer) {
		this.startHouer = startHouer;
	}


	public int getStartMinutes() {
		return startMinutes;
	}


	public void setStartMinutes(int startMinutes) {
		this.startMinutes = startMinutes;
	}


	public Timetable (int id, ArrayList<String> drivingdays, String name, int startstation, int endstation, ArrayList<Integer> middlestations, int startHouer, int startMinutes, int visits){
		
		this.setId(id);
		this.setDrivingdays(drivingdays);
		this.setName(name); 
		this.setStartstation(startstation);
		this.setEndstation(endstation);
		this.middlestations = middlestations;
		this.startHouer = startHouer;
		this.startMinutes = startMinutes;
		this.setVisits(visits);
			
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStartstation() {
		return startstation;
	}


	public void setStartstation(int startstation) {
		this.startstation = startstation;
	}


	public int getEndstation() {
		return endstation;
	}


	public void setEndstation(int endstation) {
		this.endstation = endstation;
	}


	public ArrayList<String> getDrivingdays() {
		return drivingdays;
	}


	public void setDrivingdays(ArrayList<String> drivingdays) {
		this.drivingdays = drivingdays;
	}


	public int getVisits() {
		return visits;
	}


	public void setVisits(int visits) {
		this.visits = visits;
	}

}
