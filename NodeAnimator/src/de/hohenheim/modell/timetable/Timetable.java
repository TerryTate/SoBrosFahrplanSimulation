package de.hohenheim.modell.timetable;

public class Timetable {
	
	private int id;
	private String name;
	String[] drivingdays;
	private int startstation;
	private int endstation;
	int [] middlestations; 
	
	
	public Timetable (int id, String[] drivingdays, String name, int startstation, int endstation, int[] middlestations){
		
		this.setId(id);
		this.drivingdays = drivingdays;
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

}
