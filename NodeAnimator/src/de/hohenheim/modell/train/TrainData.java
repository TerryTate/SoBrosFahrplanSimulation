package de.hohenheim.modell.train;

/**
 * This Java- Class contains the Constructor for a Train and all Getter and
 * Setter for his attributes
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class TrainData {

	int id;
	int speed;
	String typOfTrain;
	private String ladung;
	private String priority;
	private boolean finish = false;

	/**
	 * Constructor for Train
	 * 
	 * @param id
	 * @param speed
	 * @param typOfTrain
	 * @param ladung
	 * @param priority
	 * 	
	 */
	public TrainData(int id, int speed, String typOfTrain, String ladung,
			String priority, Boolean finish) {
		this.id = id;
		this.speed = speed;
		this.typOfTrain = typOfTrain;
		this.setLadung(ladung);
		this.setPriority(priority);
		this.setFinish(finish);

	}

	/**
	 * Getter for ID
	 * 
	 * @return int - TrainID
	 */
	public int getID() {
		return id;
	}

	/**
	 * Getter for Speed
	 * 
	 * @return int - TrainSpeed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Getter for Typ of Train
	 * 
	 * @return String - TypOfTrain
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
	 * @return String - TrainLadung
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
	 * @return String - Train Priority
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

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}


}
