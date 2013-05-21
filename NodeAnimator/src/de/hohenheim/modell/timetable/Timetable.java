package de.hohenheim.modell.timetable;

public class Timetable {
	
	int id;
	String name;
	String[] drivingdays;
	int startstation;
	int endstation;
	int [] middlestations; 
	
	
	public Timetable (int id, String[] drivingdays, String name, int startstation, int endstation, int[] middlestations){
		
		this.id = id;
		this.drivingdays = drivingdays;
		this.name = name; 
		this.startstation = startstation;
		this.endstation = endstation;
		this.middlestations = middlestations;
			
	}

}
