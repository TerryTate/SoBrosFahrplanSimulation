package de.hohenheim.controller.main;

public class TrainData {
	
	int id; 
	int speed; 
	String startstation; 
	
	public TrainData (int id, int speed, String startstation){
		this.id = id;
		this.speed = speed;
		this.startstation = startstation; 
		
	}

	public int getID() {
		return id;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public String getStartStation() {
		return startstation;
	}
	
}
