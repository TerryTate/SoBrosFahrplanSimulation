package de.hohenheim.modell.project;

import java.util.ArrayList;

import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;

public class Project {
	
	private int id; 
	private String name;
	private ArrayList<TrainData> TraindataProjectList;
	private ArrayList<Timetable> TimeTableProjectList;
	
	public Project(int id, String name,  ArrayList<TrainData> trainList, ArrayList<Timetable> timetableList) {
		
		this.setId(id);
		this.setName(name); 
		this.setTraindataProjectList(trainList);
		this.setTimeTableProjectList(timetableList);
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

	public ArrayList<TrainData> getTraindataProjectList() {
		return TraindataProjectList;
	}

	public void setTraindataProjectList(ArrayList<TrainData> traindataProjectList) {
		TraindataProjectList = traindataProjectList;
	}

	public ArrayList<Timetable> getTimeTableProjectList() {
		return TimeTableProjectList;
	}

	public void setTimeTableProjectList(ArrayList<Timetable> timeTableProjectList) {
		TimeTableProjectList = timeTableProjectList;
	}
	
	

}
