package de.hohenheim.modell.train;

public class TrainData {
	
	int id; 
	int speed; 
	String typOfTrain; 
	
	public TrainData (int id, int speed, String typOfTrain){
		this.id = id;
		this.speed = speed;
		this.typOfTrain = typOfTrain; 
		
	}

	public int getID() {
		return id;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public String gettypOfTrain() {
		return typOfTrain;
	}
	
}
