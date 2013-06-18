package de.hohenheim.modell.timetable;

import java.util.ArrayList;
/**
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
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> getMiddlestations() {
		return middlestations;
	}

	/**
	 * 
	 * @param middlestations
	 */
	public void setMiddlestations(ArrayList<Integer> middlestations) {
		this.middlestations = middlestations;
	}

	/**
	 * 
	 * @return
	 */
	public int getStartHouer() {
		return startHouer;
	}

	/**
	 * 
	 * @param startHouer
	 */
	public void setStartHouer(int startHouer) {
		this.startHouer = startHouer;
	}

	/**
	 * 
	 * @return
	 */
	public int getStartMinutes() {
		return startMinutes;
	}

	/**
	 * 
	 * @param startMinutes
	 */
	public void setStartMinutes(int startMinutes) {
		this.startMinutes = startMinutes;
	}

	/**
	 * 
	 * @param id
	 * @param drivingdays
	 * @param name
	 * @param startstation
	 * @param endstation
	 * @param middlestations
	 * @param startHouer
	 * @param startMinutes
	 * @param visits
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
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public int getStartstation() {
		return startstation;
	}

	/**
	 * 
	 * @param startstation
	 */
	public void setStartstation(int startstation) {
		this.startstation = startstation;
	}

	/**
	 * 
	 * @return
	 */
	public int getEndstation() {
		return endstation;
	}
	
	/**
	 * 
	 * @param endstation
	 */
	public void setEndstation(int endstation) {
		this.endstation = endstation;
	}

    /**
     * 
     * @return
     */
	public ArrayList<String> getDrivingdays() {
		return drivingdays;
	}

	/**
	 * 
	 * @param drivingdays
	 */
	public void setDrivingdays(ArrayList<String> drivingdays) {
		this.drivingdays = drivingdays;
	}

    /**
     * 
     * @return
     */
	public int getVisits() {
		return visits;
	}

    /**
     * 
     * @param visits
     */
	public void setVisits(int visits) {
		this.visits = visits;
	}

}
