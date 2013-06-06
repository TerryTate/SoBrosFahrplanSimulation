package de.hohenheim.modell.timetable;

import java.util.ArrayList;

public class Timetable {
	
	private int id;
	private String name;
	private ArrayList<String> drivingdays;
	private int startstation;
	private int endstation;
	ArrayList<Integer> middlestations; 
	
	
	public Timetable (int id, ArrayList<String> drivingdays, String name, int startstation, int endstation, ArrayList<Integer> middlestations){
		
		this.setId(id);
		this.setDrivingdays(drivingdays);
		this.setName(name); 
		this.setStartstation(startstation);
		this.setEndstation(endstation);
		this.middlestations = middlestations;
			
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

}
