package de.hohenheim.modell.train;

public class TrainData {
	
	int id; 
	int speed; 
	String typOfTrain; 
	private String ladung;
	private String priority;
	boolean anim;
	
	
	public TrainData (int id, int speed, String typOfTrain, String ladung, String priority, boolean anim){
		this.id = id;
		this.speed = speed;
		this.typOfTrain = typOfTrain; 
		this.setLadung(ladung);
		this.setPriority(priority);
		this.anim = anim;
		
	}

	public int getID() {
		return id;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public String getTypOfTrain() {
		return typOfTrain;
	}

	public void setID(int id) {
		this.id = id;	
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;	
	}
	public void setTypOfTrain(String typOfTrain) {
		this.typOfTrain = typOfTrain;	
	}

	public String getLadung() {
		return ladung;
	}

	public void setLadung(String ladung) {
		this.ladung = ladung;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public boolean getAnim() {
		
		return anim;
	}

	public void setAnim(boolean anim) {
		this.anim = anim;
		
	}
	
}
