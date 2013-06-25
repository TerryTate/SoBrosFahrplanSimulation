package de.hohenheim.modell.project;

import java.util.ArrayList;

import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;

/**
 * 
 * @author Arthur Kaul
 *
 */
public class Project {
	
	private int id; 
	private String name;
	private ArrayList<TrainData> TraindataProjectList;
	private ArrayList<Timetable> TimeTableProjectList;
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param trainList
	 * @param timetableList
	 */
	public Project(int id, String name,  ArrayList<TrainData> trainList, ArrayList<Timetable> timetableList) {
		
		this.setId(id);
		this.setName(name); 
		this.setTraindataProjectList(trainList);
		this.setTimeTableProjectList(timetableList);
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
	public ArrayList<TrainData> getTraindataProjectList() {
		return TraindataProjectList;
	}

	/**
	 * 
	 * @param traindataProjectList
	 */
	public void setTraindataProjectList(ArrayList<TrainData> traindataProjectList) {
		TraindataProjectList = traindataProjectList;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Timetable> getTimeTableProjectList() {
		return TimeTableProjectList;
	}

	/**
	 * 
	 * @param timeTableProjectList
	 */
	public void setTimeTableProjectList(ArrayList<Timetable> timeTableProjectList) {
		TimeTableProjectList = timeTableProjectList;
	}

	public Timetable getTimetable(int figureId) {
		int k = 0;
		Timetable tt = null;
		for(TrainData td : TraindataProjectList){
		    
			if(figureId == td.getID()){
				tt = TimeTableProjectList.get(k);
			}
			k++;
		}
		return tt;
	}
	
	

}
