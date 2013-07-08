package de.hohenheim.modell.train;

/**
 *  This Java- Class contains the Constructor for a Train and all Getter and Setter for 
 * his attributes
 * 
 * @author Arthur Kaul
 *
 */
public class TrainData {
	
	int id; 
	int speed; 
	String typOfTrain; 
	private String ladung;
	private String priority;
	boolean anim;
	
	/**
	 * Constructor for Train
	 * 
	 * @param id
	 * @param speed
	 * @param typOfTrain
	 * @param ladung
	 * @param priority
	 * @param anim
	 */
	public TrainData (int id, int speed, String typOfTrain, String ladung, String priority){
		this.id = id;
		this.speed = speed;
		this.typOfTrain = typOfTrain; 
		this.setLadung(ladung);
		this.setPriority(priority);
		this.anim = anim;
		
	}
    
	/**
	 * Getter for ID
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Getter for Speed 
	 * 
	 * @return
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Getter for Typ of Train
	 * 
	 * @return
	 */
	public String getTypOfTrain() {
		return typOfTrain;
	}

	/**
	 * Setter for ID
	 * 
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;	
	}
	
	/**
	 * Setter for Speed
	 * 
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;	
	}
	
	/**
	 * Setter for Typ of Train
	 * 
	 * @param typOfTrain
	 */
	public void setTypOfTrain(String typOfTrain) {
		this.typOfTrain = typOfTrain;	
	}

	/**
	 * Getter for ladung
	 * 
	 * @return
	 */
	public String getLadung() {
		return ladung;
	}

	/**
	 * Setter for Ladung
	 * 
	 * @param ladung
	 */
	public void setLadung(String ladung) {
		this.ladung = ladung;
	}

	/**
	 * Getter for Priority
	 * 
	 * @return
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Setter for Priority
	 * 
	 * @param priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
}
