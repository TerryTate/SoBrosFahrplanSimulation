package de.hohenheim.modell.train;

/**
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
	 * 
	 * @param id
	 * @param speed
	 * @param typOfTrain
	 * @param ladung
	 * @param priority
	 * @param anim
	 */
	public TrainData (int id, int speed, String typOfTrain, String ladung, String priority, boolean anim){
		this.id = id;
		this.speed = speed;
		this.typOfTrain = typOfTrain; 
		this.setLadung(ladung);
		this.setPriority(priority);
		this.anim = anim;
		
	}
    
	/**
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public String getTypOfTrain() {
		return typOfTrain;
	}

	/**
	 * 
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;	
	}
	
	/**
	 * 
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;	
	}
	
	/**
	 * 
	 * @param typOfTrain
	 */
	public void setTypOfTrain(String typOfTrain) {
		this.typOfTrain = typOfTrain;	
	}

	/**
	 * 
	 * @return
	 */
	public String getLadung() {
		return ladung;
	}

	/**
	 * 
	 * @param ladung
	 */
	public void setLadung(String ladung) {
		this.ladung = ladung;
	}

	/**
	 * 
	 * @return
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * 
	 * @param priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getAnim() {
		
		return anim;
	}

	/**
	 * 
	 * @param anim
	 */
	public void setAnim(boolean anim) {
		this.anim = anim;
		
	}
	
}
