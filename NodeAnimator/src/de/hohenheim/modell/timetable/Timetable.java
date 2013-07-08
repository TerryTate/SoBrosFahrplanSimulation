package de.hohenheim.modell.timetable;

import java.util.ArrayList;
/**
 * This Java- Class contains the Constructor for a Timetable and all Getter and Setter for 
 * his attributes
 * 
 * @author Arthur Kaul
 *
 */
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
	private boolean drive = false;
	private boolean blocked = false;
	
	/**
	 * Constructor for Timetable
	 * 
	 * @param id - int 
	 * @param drivingdays- Arraylist
	 * @param name - String
	 * @param startstation - int 
	 * @param endstation - int 
	 * @param middlestations - Arraylist
	 * @param startHouer - int 
	 * @param startMinutes - int 
	 * @param visits - int 
	 */
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
	
	/**
	 * Getter for middlestations
	 * 
	 * @return
	 */
	public ArrayList<Integer> getMiddlestations() {
		return middlestations;
	}

	/**
	 * Setter for middlestations
	 * 
	 * @param middlestations
	 */
	public void setMiddlestations(ArrayList<Integer> middlestations) {
		this.middlestations = middlestations;
	}

	/**
	 * Getter for start hour
	 * 
	 * @return
	 */
	public int getStartHouer() {
		return startHouer;
	}

	/**
	 * Setter for start Hour
	 * 
	 * @param startHouer
	 */
	public void setStartHouer(int startHouer) {
		this.startHouer = startHouer;
	}

	/**
	 * Getter for startMinutes
	 * 
	 * @return
	 */
	public int getStartMinutes() {
		return startMinutes;
	}

	/**
	 * Setter for startMinutes
	 * 
	 * @param startMinutes
	 */
	public void setStartMinutes(int startMinutes) {
		this.startMinutes = startMinutes;
	}

	/**
	 * Getter for ID
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for ID
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for Name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for startstation
	 * 
	 * @return
	 */
	public int getStartstation() {
		return startstation;
	}

	/**
	 * Setter for startstation
	 * 
	 * @param startstation
	 */
	public void setStartstation(int startstation) {
		this.startstation = startstation;
	}

	/**
	 * Getter for endstation
	 * 
	 * @return
	 */
	public int getEndstation() {
		return endstation;
	}
	
	/**
	 * Setter for Endstation
	 * 
	 * @param endstation
	 */
	public void setEndstation(int endstation) {
		this.endstation = endstation;
	}

    /**
     * Getter for drivingdays
     * 
     * @return
     */
	public ArrayList<String> getDrivingdays() {
		return drivingdays;
	}

	/**
	 * Setter for drivingdays
	 * 
	 * @param drivingdays
	 */
	public void setDrivingdays(ArrayList<String> drivingdays) {
		this.drivingdays = drivingdays;
	}

    /**
     * Getter for visits
     * 
     * @return
     */
	public int getVisits() {
		return visits;
	}

    /**
     * Setter for visits
     * 
     * @param visits
     */
	public void setVisits(int visits) {
		this.visits = visits;
	}

	/**
	 * Getter for drive
	 * 
	 * @return
	 */
	public boolean isDrived() {
		return drive ;
	}

	/**
	 * Setter for drive
	 * 
	 * @param drive
	 */
	public void setDrived(boolean drive) {
		this.drive = drive;
		
	}

	/**
	 * Getter for blocked
	 * 
	 * @return
	 */
	public boolean isBlocked() {
		return blocked ;
	}

	/**
	 * Setter for blocked
	 * 
	 * @param blocked
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
		
	}
	
	

}
