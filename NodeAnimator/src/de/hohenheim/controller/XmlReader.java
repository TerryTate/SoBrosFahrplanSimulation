package de.hohenheim.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;

/**
 * Class to read XML Documents that stored trains and time tables
 * 
 * @author Matze
 * 
 */
public class XmlReader {
	

	/**
	 * Method to load one Single Train from XML File
	 * 
	 * @param fileName
	 *            Selected file to load the right and by the user chosen
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
		} catch (NullPointerException e){
		
		}
		
		
		TrainData returnTrain = new TrainData(0, 0, null, null, null, false);
		try{
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
		
		}catch (NullPointerException e){
			
		}
		
		return returnTrain;
	}
	
	
	
	public static Timetable loadSingleTimeTable(String fileName) throws NullPointerException{
		
		SAXBuilder saxBuilder = new SAXBuilder();
		Element root = null;
		Document doc = new Document();
		
		try {
			doc = saxBuilder.build(fileName);
			root = doc.getRootElement();
		}catch (Exception e){
			e.fillInStackTrace();
		}
		
		
		Timetable returnTimeTable = new Timetable(0, null, null, 0, 0, null, 0, 0, 0); 
		
		Element iD = root.getChild("ID");
		int iDValue = Integer.parseInt(iD.getValue());
		returnTimeTable.setId(iDValue);
		
		Element name = root.getChild("Name");
		String nameValue = name.getValue();
		returnTimeTable.setName(nameValue);
		
		
		ArrayList<String> drivingdays = new ArrayList<String>();
		for (int i = 0; i <= 7; i++){
			Element singleDay = root.getChild("DrivingDay" + (i + 1));
			try{
			String singleDayValue = singleDay.getValue();
			drivingdays.add(singleDayValue);
			}catch(NullPointerException e){
				
			}
			
		}
	
		returnTimeTable.setDrivingdays(drivingdays);
		
		
		Element startstation = root.getChild("StartStation");
		Integer startStationValue = Integer.parseInt(startstation.getValue());
		returnTimeTable.setStartstation(startStationValue);
		
		Element endstation = root.getChild("EndStation");
		Integer endstationValue = Integer.parseInt(endstation.getValue());
		returnTimeTable.setEndstation(endstationValue);
		
		ArrayList<Integer> middlestations = new ArrayList<Integer>(); 
		for (int j = 0; j <=7; j++){
			Element singleMStation = root.getChild("MiddleStation" + (j + 1));
			int singleMStationValue = Integer.parseInt(singleMStation.getValue());
			middlestations.add(singleMStationValue);
		}
		returnTimeTable.setMiddlestations(middlestations);
		
		Element startHouer = root.getChild("StartHour");
		Integer startHourValue = Integer.parseInt(startHouer.getValue());
		returnTimeTable.setStartHouer(startHourValue);
		
		Element startMinutes = root.getChild("StarMinutes");
		Integer startMinutesValue = Integer.parseInt(startMinutes.getValue());
		returnTimeTable.setStartMinutes(startMinutesValue);
		
		
		return returnTimeTable;
		
	}
	
}
