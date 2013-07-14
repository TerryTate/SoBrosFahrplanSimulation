package de.hohenheim.modell.project;

import java.util.ArrayList;

import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;

/**
 * This Java- Class contains the Constructor for a Project and all Getter and
 * Setter for his attributes
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class Project {

	private int id;
	private String name;
	private ArrayList<TrainData> TraindataProjectList;
	private ArrayList<Timetable> TimeTableProjectList;

	/**
	 * Constructor for Projects
	 * 
	 * @param id
	 *            - int
	 * @param name
	 *            - String
	 * @param trainList
	 *            - ArrayList
	 * @param timetableList
	 *            - ArrayList
	 */
	public Project(int id, String name, ArrayList<TrainData> trainList,
			ArrayList<Timetable> timetableList) {

		this.setId(id);
		this.setName(name);
		this.setTraindataProjectList(trainList);
		this.setTimeTableProjectList(timetableList);
	}

	/**
	 * Getter for ID
	 * 
	 * @return int ID
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
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for Name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for Train list
	 * 
	 * @return TraindataProjectList
	 */
	public ArrayList<TrainData> getTraindataProjectList() {
		return TraindataProjectList;
	}

	/**
	 * Setter for Train list
	 * 
	 * @param traindataProjectList
	 */
	public void setTraindataProjectList(
			ArrayList<TrainData> traindataProjectList) {
		TraindataProjectList = traindataProjectList;
	}

	/**
	 * Getter for Timetable list
	 * 
	 * @return TimetableProjectList
	 */
	public ArrayList<Timetable> getTimeTableProjectList() {
		return TimeTableProjectList;
	}

	/**
	 * Setter for Timetable list
	 * 
	 * @param timeTableProjectList
	 */
	public void setTimeTableProjectList(
			ArrayList<Timetable> timeTableProjectList) {
		TimeTableProjectList = timeTableProjectList;
	}

	/**
	 * Method get the timetable witch is part of the TrainID
	 * 
	 * @param figureId
	 * @return Timetable
	 */
	public Timetable getTimetable(int figureId) {
		int k = 0;
		Timetable tt = null;
		for (TrainData td : TraindataProjectList) {

			if (figureId == td.getID()) {
				tt = TimeTableProjectList.get(k);
			}
			k++;
		}
		return tt;
	}

}
