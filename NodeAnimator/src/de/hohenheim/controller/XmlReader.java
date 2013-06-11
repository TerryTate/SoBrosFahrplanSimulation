package de.hohenheim.controller;

import java.io.IOException;

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
		}
		
		TrainData returnTrain = new TrainData(0, 0, null, null, null);
		
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

		
		return returnTrain;
	}
	
	
	
	public static Timetable loadSingleTimeTable(String fileName){
		
		Timetable returnTimeTable = null;
		
		return returnTimeTable;
		
	}
	
}
