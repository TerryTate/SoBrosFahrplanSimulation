package de.hohenheim.controller;


import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;

/**
* This class provides method/s to save trains and time tables (train schedules)
* using XML-files.
* 
* @author Matze
* 
*/
public class XmlWriter {

/**
 * Static method to write XML-file that provides the trains and time tables
 * (train schedules) we want to save. Is static so you don't have to
 * instantiate the XmlWriter as object to use this method.
 * 
 * (Note: JDOM = Document Object Model)
 * 
 * @param fileName
 *            Name of the saved XML-File as String, importent to distinguish
 *            the different trains and time tables.
 */
 public static void writeToXML(String fileName) {
	
	
		/*
	 * Root Element of the JDOM Tree to attach all other Elements
	 * Going through the list of all trains
	 */
    	org.jdom2.Element rootTrain = new org.jdom2.Element("Train");
	    for (TrainData tD : Main.trainListAll){

		org.jdom2.Element iD = new org.jdom2.Element("ID");
		rootTrain.addContent(iD);
		iD.setText(String.valueOf(tD.getID()));
		
		
		org.jdom2.Element typeOfTrain = new org.jdom2.Element("TrainType");
		rootTrain.addContent(typeOfTrain);
		typeOfTrain.setText(String.valueOf(tD.getTypOfTrain()));
		

		org.jdom2.Element priority = new org.jdom2.Element("Priority");
		rootTrain.addContent(priority);
		priority.setText(String.valueOf(tD.getPriority()));
		
		
		org.jdom2.Element speed = new org.jdom2.Element("Speed");
		rootTrain.addContent(speed);
		speed.setText(String.valueOf(tD.getSpeed()));
		
		
		org.jdom2.Element ladung = new org.jdom2.Element("Lading");
		rootTrain.addContent(iD);
		ladung.setText(String.valueOf(tD.getLadung()));
		
		/*
		 * XML Document gets instantiated
		 */
		org.jdom2.Document doc = new org.jdom2.Document(rootTrain);
		
		
		/*
		 * FileOutputStream with Name of  the file + ID
		 * XMLOutputter with "prettyFormat"
		 */
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName + String.valueOf(tD.getID()));
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.output(doc, fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (IOException e) {
			System.err.println(e);
			e.fillInStackTrace();
		}
			
		
			
			
		
	}
	
		
		
  }

}

		