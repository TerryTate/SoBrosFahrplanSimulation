package de.hohenheim.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;

/**
 * Class to read XML Documents that stored trains and time tables
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */
public class XmlReader {

	
	//---------------------------Befüllte Listen für das Laden von allem beim Programmstart------------------------
	public static ArrayList<Project> toLoadProjectList = new ArrayList<Project>();

	public static ArrayList<Timetable> toLoadTimeTableList = new ArrayList<Timetable>();

	public static ArrayList<TrainData> toLoadTrainDataList = new ArrayList<TrainData>();
	//------------------------------------------------------------------------------------------------------------

	/**
	 * Method to load all Data stored in the settings/" ".xml file
	 */
	public static void loadAllData() {

		SAXBuilder saxBuilder = new SAXBuilder();
		Element rootOfAll = null;
		Document doc;
		String fileName = new String("settings/SaveAll.xml");

		try {
			doc = saxBuilder.build(fileName);
			rootOfAll = doc.getRootElement();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {

		}

		Element allProjects = rootOfAll.getChild("AllProjects");
        
		try {
		for (int r = 0; r < allProjects.getChildren().size(); r++) {

			Project returnProject = new Project(0, null, null, null);

			Element root = allProjects.getChildren().get(r);

			Element idProject = root.getChild("ID");
			int valueOfidProject = Integer.parseInt(idProject.getValue());
			returnProject.setId(valueOfidProject);

			Element nameProject = root.getChild("Name");
			returnProject.setName(nameProject.getValue());

			// -----------------------------------------------------------------------------------------

			Element trains = root.getChild("Trains");

			ArrayList<TrainData> returnArrayListTD = new ArrayList<TrainData>();

			for (int i = 0; i < trains.getChildren().size(); i++) {

				TrainData addingTrain = new TrainData(0, 0, null, null, null, false);

				Element train = trains.getChildren().get(i);

				try {
					Element id = train.getChild("ID");
					int iDValue = Integer.parseInt(id.getValue());
					addingTrain.setID(iDValue);

					Element typeOfTrain = train.getChild("TypeOfTrain");
					String typOfTrainValue = typeOfTrain.getValue();
					addingTrain.setTypOfTrain(typOfTrainValue);

					Element priority = train.getChild("Priority");
					String priorityValue = priority.getValue();
					addingTrain.setPriority(priorityValue);

					Element speed = train.getChild("Speed");
					int speedValue = Integer.parseInt(speed.getValue());
					addingTrain.setSpeed(speedValue);

					Element ladung = train.getChild("Ladung");
					String ladungValue = ladung.getValue();
					addingTrain.setLadung(ladungValue);

				} catch (NullPointerException e) {
					e.fillInStackTrace();
				}

				returnArrayListTD.add(addingTrain);
			}

			returnProject.setTraindataProjectList(returnArrayListTD);

			// ----------------------------------------------------------------------------------
			// New Node (Element) in the JDOM Tree including all timeTable
			// values

			Element timeTables = root.getChild("TimeTables");

			ArrayList<Timetable> returnArrayListTT = new ArrayList<Timetable>();

			for (int t = 0; t < timeTables.getChildren().size(); t++) {

				Timetable addingTimeTable = new Timetable(0, null, null, 0, 0,
						null, 0, 0, 0);

				Element timeTable = timeTables.getChildren().get(t);

				Element iD = timeTable.getChild("ID");
				int iDValue = Integer.parseInt(iD.getValue());
				addingTimeTable.setId(iDValue);

				Element name = timeTable.getChild("Name");
				String nameValue = name.getValue();
				addingTimeTable.setName(nameValue);

				ArrayList<String> drivingdays = new ArrayList<String>();
				for (int i = 0; i <= 7; i++) {
					Element singleDay = timeTable.getChild("DrivingDay"
							+ (i + 1));
					try {
						String singleDayValue = singleDay.getValue();
						drivingdays.add(singleDayValue);
					} catch (NullPointerException e) {

					}

				}

				addingTimeTable.setDrivingdays(drivingdays);

				Element stations = timeTable.getChild("Stations");

				Element startstation = stations.getChild("StartStation");
				Integer startStationValue = Integer.parseInt(startstation
						.getValue());
				addingTimeTable.setStartstation(startStationValue);

				Element endstation = stations.getChild("EndStation");
				Integer endstationValue = Integer.parseInt(endstation
						.getValue());
				addingTimeTable.setEndstation(endstationValue);

				Element allMiddleStations = stations
						.getChild("AllMiddleStations");

				ArrayList<Integer> middlestation = new ArrayList<Integer>();

				for (int j = 0; j < allMiddleStations.getChildren().size(); j++) {
					Element singleMStation = allMiddleStations.getChildren()
							.get(j);
					int singleMStationValue = Integer.parseInt(singleMStation
							.getValue());
					middlestation.add(singleMStationValue);
				}
				addingTimeTable.setMiddlestations(middlestation);

				Element time = timeTable.getChild("Time");

				Element startHouer = time.getChild("StartHouer");
				Integer startHourValue = Integer
						.parseInt(startHouer.getValue());
				addingTimeTable.setStartHouer(startHourValue);

				Element startMinutes = time.getChild("StarMinutes");
				Integer startMinutesValue = Integer.parseInt(startMinutes
						.getValue());
				addingTimeTable.setStartMinutes(startMinutesValue);

				returnArrayListTT.add(addingTimeTable);
			}

			returnProject.setTimeTableProjectList(returnArrayListTT);

			// -----------------------------------------------------------------

			Main.getProjectListAll().add(returnProject);
		}
		
		
		Element allTimeTables = rootOfAll.getChild("AllTimeTables");
		
		for (int t = 0; t < allTimeTables.getChildren().size(); t++) {
			
			
			Timetable returnTimeTable = new Timetable(0, null, null, 0, 0, null, 0,
					0, 0);
			
			Element root = allTimeTables.getChildren().get(t);

			Element iD = root.getChild("ID");
			int iDValue = Integer.parseInt(iD.getValue());
			returnTimeTable.setId(iDValue);

			Element name = root.getChild("Name");
			String nameValue = name.getValue();
			returnTimeTable.setName(nameValue);

			ArrayList<String> drivingdays = new ArrayList<String>();
			for (int i = 0; i <= 7; i++) {
				Element singleDay = root.getChild("DrivingDay" + (i + 1));
				try {
					String singleDayValue = singleDay.getValue();
					drivingdays.add(singleDayValue);
				} catch (NullPointerException e) {

				}

			}

			returnTimeTable.setDrivingdays(drivingdays);

			Element stations = root.getChild("Stations");

			Element startstation = stations.getChild("StartStation");
			Integer startStationValue = Integer.parseInt(startstation.getValue());
			returnTimeTable.setStartstation(startStationValue);

			Element endstation = stations.getChild("EndStation");
			Integer endstationValue = Integer.parseInt(endstation.getValue());
			returnTimeTable.setEndstation(endstationValue);

			Element middlestations = stations.getChild("Middlestations");

			ArrayList<Integer> middlestation = new ArrayList<Integer>();

			for (int j = 0; j < middlestations.getChildren().size(); j++) {
				Element singleMStation = middlestations.getChildren().get(j);
				int singleMStationValue = Integer.parseInt(singleMStation
						.getValue());
				middlestation.add(singleMStationValue);
			}
			returnTimeTable.setMiddlestations(middlestation);

			Element time = root.getChild("Time");

			Element startHouer = time.getChild("StartHouer");
			Integer startHourValue = Integer.parseInt(startHouer.getValue());
			returnTimeTable.setStartHouer(startHourValue);

			Element startMinutes = time.getChild("StarMinutes");
			Integer startMinutesValue = Integer.parseInt(startMinutes.getValue());
			returnTimeTable.setStartMinutes(startMinutesValue);

			
			
			Main.getTimetableListAll().add(returnTimeTable);
			
		}
		
		
		Element rootOfAllTrain = rootOfAll.getChild("AllTrainData");
		try {
		for (int z = 0; z < rootOfAllTrain.getChildren().size(); z++){
			
			TrainData returnTrain = new TrainData(0, 0, null, null, null, false);
			
			Element root = rootOfAllTrain.getChildren().get(z);
			
			
				Element id = root.getChild("ID");
				int iDValue = Integer.parseInt(id.getValue());
				returnTrain.setID(iDValue);

				Element typeOfTrain = root.getChild("TypeOfTrain");
				String typOfTrainValue = typeOfTrain.getValue();
				returnTrain.setTypOfTrain(typOfTrainValue);

				Element priority = root.getChild("Priority");
				String priorityValue = priority.getValue();
				returnTrain.setPriority(priorityValue);

				Element speed = root.getChild("Speed");
				int speedValue = Integer.parseInt(speed.getValue());
				returnTrain.setSpeed(speedValue);

				Element ladung = root.getChild("Ladung");
				String ladungValue = ladung.getValue();
				returnTrain.setLadung(ladungValue);

		
			
			Main.getTrainListAll().add(returnTrain);
			
			
		}
		} catch (NullPointerException e) {

		}
		} catch (NullPointerException e) {

		}
		
		

	}

	/**
	 * Method to load one Single Train from XML File
	 * 
	 * @param fileName
	 *            Selected file to load the right and by the user chosen
	 * @return TrainData
	 */
	public static TrainData loadSingleTrain(String fileName) {

		SAXBuilder saxBuilder = new SAXBuilder();
		Element root = null;
		Document doc;

		try {
			doc = saxBuilder.build(fileName);
			root = doc.getRootElement();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {

		}

		TrainData returnTrain = new TrainData(0, 0, null, null, null, false);
		try {
			Element id = root.getChild("ID");
			int iDValue = Integer.parseInt(id.getValue());
			returnTrain.setID(iDValue);

			Element typeOfTrain = root.getChild("TypeOfTrain");
			String typOfTrainValue = typeOfTrain.getValue();
			returnTrain.setTypOfTrain(typOfTrainValue);

			Element priority = root.getChild("Priority");
			String priorityValue = priority.getValue();
			returnTrain.setPriority(priorityValue);

			Element speed = root.getChild("Speed");
			int speedValue = Integer.parseInt(speed.getValue());
			returnTrain.setSpeed(speedValue);

			Element ladung = root.getChild("Ladung");
			String ladungValue = ladung.getValue();
			returnTrain.setLadung(ladungValue);

		} catch (NullPointerException e) {

		}

		return returnTrain;
	}

	/**
	 * Method to load single TimeTable from XML-File using SAXBuilder
	 * 
	 * @param fileName
	 *            Path and Name of XML-File with the save timeTable
	 * @return TimeTable
	 */
	public static Timetable loadSingleTimeTable(String fileName) {

		SAXBuilder saxBuilder = new SAXBuilder();
		Element root = null;
		Document doc = new Document();

		try {
			doc = saxBuilder.build(fileName);
			root = doc.getRootElement();
		} catch (Exception e) {
			e.fillInStackTrace();
		}

		Timetable returnTimeTable = new Timetable(0, null, null, 0, 0, null, 0,
				0, 0);

		Element iD = root.getChild("ID");
		int iDValue = Integer.parseInt(iD.getValue());
		returnTimeTable.setId(iDValue);

		Element name = root.getChild("Name");
		String nameValue = name.getValue();
		returnTimeTable.setName(nameValue);

		ArrayList<String> drivingdays = new ArrayList<String>();
		for (int i = 0; i <= 7; i++) {
			Element singleDay = root.getChild("DrivingDay" + (i + 1));
			try {
				String singleDayValue = singleDay.getValue();
				drivingdays.add(singleDayValue);
			} catch (NullPointerException e) {

			}

		}

		returnTimeTable.setDrivingdays(drivingdays);

		Element stations = root.getChild("Stations");

		Element startstation = stations.getChild("StartStation");
		Integer startStationValue = Integer.parseInt(startstation.getValue());
		returnTimeTable.setStartstation(startStationValue);

		Element endstation = stations.getChild("EndStation");
		Integer endstationValue = Integer.parseInt(endstation.getValue());
		returnTimeTable.setEndstation(endstationValue);

		Element middlestations = stations.getChild("Middlestations");

		ArrayList<Integer> middlestation = new ArrayList<Integer>();

		for (int j = 0; j < middlestations.getChildren().size(); j++) {
			Element singleMStation = middlestations.getChildren().get(j);
			int singleMStationValue = Integer.parseInt(singleMStation
					.getValue());
			middlestation.add(singleMStationValue);
		}
		returnTimeTable.setMiddlestations(middlestation);

		Element time = root.getChild("Time");

		Element startHouer = time.getChild("StartHouer");
		Integer startHourValue = Integer.parseInt(startHouer.getValue());
		returnTimeTable.setStartHouer(startHourValue);

		Element startMinutes = time.getChild("StarMinutes");
		Integer startMinutesValue = Integer.parseInt(startMinutes.getValue());
		returnTimeTable.setStartMinutes(startMinutesValue);

		return returnTimeTable;

	}

	/**
	 * Method to load a single project (including all trains and timetables)
	 * from one XML-File
	 * 
	 * @param fileName
	 *            path and name from the xml-file that will be loaded into the
	 *            SoBros programm
	 * @return Project
	 */
	public static Project loadSingelProject(String fileName) {

		Project returnProject = new Project(0, null, null, null);

		SAXBuilder saxBuilder = new SAXBuilder();
		Element root = null;
		Document doc;

		try {
			doc = saxBuilder.build(fileName);
			root = doc.getRootElement();
		} catch (Exception e) {
			e.fillInStackTrace();
		}

		Element idProject = root.getChild("ID");
		int valueOfidProject = Integer.parseInt(idProject.getValue());
		returnProject.setId(valueOfidProject);

		Element nameProject = root.getChild("Name");
		returnProject.setName(nameProject.getValue());

		// -----------------------------------------------------------------------------------------

		Element trains = root.getChild("Trains");

		ArrayList<TrainData> returnArrayListTD = new ArrayList<TrainData>();

		for (int i = 0; i < trains.getChildren().size(); i++) {

			TrainData addingTrain = new TrainData(0, 0, null, null, null, false);

			Element train = trains.getChildren().get(i);

			try {
				Element id = train.getChild("ID");
				int iDValue = Integer.parseInt(id.getValue());
				addingTrain.setID(iDValue);

				Element typeOfTrain = train.getChild("TypeOfTrain");
				String typOfTrainValue = typeOfTrain.getValue();
				addingTrain.setTypOfTrain(typOfTrainValue);

				Element priority = train.getChild("Priority");
				String priorityValue = priority.getValue();
				addingTrain.setPriority(priorityValue);

				Element speed = train.getChild("Speed");
				int speedValue = Integer.parseInt(speed.getValue());
				addingTrain.setSpeed(speedValue);

				Element ladung = train.getChild("Ladung");
				String ladungValue = ladung.getValue();
				addingTrain.setLadung(ladungValue);

			} catch (NullPointerException e) {
				e.fillInStackTrace();
			}

			returnArrayListTD.add(addingTrain);
		}

		returnProject.setTraindataProjectList(returnArrayListTD);

		// ----------------------------------------------------------------------------------
		// New Node (Element) in the JDOM Tree including all timeTable values

		Element timeTables = root.getChild("TimeTables");

		ArrayList<Timetable> returnArrayListTT = new ArrayList<Timetable>();

		for (int t = 0; t < timeTables.getChildren().size(); t++) {

			Timetable addingTimeTable = new Timetable(0, null, null, 0, 0,
					null, 0, 0, 0);

			Element timeTable = timeTables.getChildren().get(t);

			Element iD = timeTable.getChild("ID");
			int iDValue = Integer.parseInt(iD.getValue());
			addingTimeTable.setId(iDValue);

			Element name = timeTable.getChild("Name");
			String nameValue = name.getValue();
			addingTimeTable.setName(nameValue);

			ArrayList<String> drivingdays = new ArrayList<String>();
			for (int i = 0; i <= 7; i++) {
				Element singleDay = timeTable.getChild("DrivingDay" + (i + 1));
				try {
					String singleDayValue = singleDay.getValue();
					drivingdays.add(singleDayValue);
				} catch (NullPointerException e) {

				}

			}

			addingTimeTable.setDrivingdays(drivingdays);

			Element stations = timeTable.getChild("Stations");

			Element startstation = stations.getChild("StartStation");
			Integer startStationValue = Integer.parseInt(startstation
					.getValue());
			addingTimeTable.setStartstation(startStationValue);

			Element endstation = stations.getChild("EndStation");
			Integer endstationValue = Integer.parseInt(endstation.getValue());
			addingTimeTable.setEndstation(endstationValue);

			Element allMiddleStations = stations.getChild("AllMiddleStations");

			ArrayList<Integer> middlestation = new ArrayList<Integer>();

			for (int j = 0; j < allMiddleStations.getChildren().size(); j++) {
				Element singleMStation = allMiddleStations.getChildren().get(j);
				int singleMStationValue = Integer.parseInt(singleMStation
						.getValue());
				middlestation.add(singleMStationValue);
			}
			addingTimeTable.setMiddlestations(middlestation);

			Element time = timeTable.getChild("Time");

			Element startHouer = time.getChild("StartHouer");
			Integer startHourValue = Integer.parseInt(startHouer.getValue());
			addingTimeTable.setStartHouer(startHourValue);

			Element startMinutes = time.getChild("StarMinutes");
			Integer startMinutesValue = Integer.parseInt(startMinutes
					.getValue());
			addingTimeTable.setStartMinutes(startMinutesValue);

			returnArrayListTT.add(addingTimeTable);
		}

		returnProject.setTimeTableProjectList(returnArrayListTT);

		return returnProject;

	}
}
