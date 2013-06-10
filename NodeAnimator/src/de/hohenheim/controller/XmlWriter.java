package de.hohenheim.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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

	}

	
	
	
	
	/**
	 * Method to save single selected Trains in one XML File
	 * 
	 * @param fileName
	 *            Name of SaveFile user gives
	 * @param tD
	 *            The user selected train which will be saved in the XML File
	 */
	public static void saveSingleTrain(String fileName, TrainData tD) {
		
		Element root = new Element("Train");
		
		Element id = new Element("ID");
		root.addContent(id);
		id.setText(String.valueOf(tD.getID()));
		
		Element typeOfTrain = new Element("TypeOfTrain");
		root.addContent(typeOfTrain);
		typeOfTrain.setText(String.valueOf(tD.getTypOfTrain()));
		
		Element priority = new Element("Priority");
		root.addContent(priority);
		priority.setText(String.valueOf(tD.getPriority()));
		
		Element speed = new Element("Speed");
		root.addContent(speed);
		speed.setText(String.valueOf(tD.getSpeed()));
		
		Element ladung = new Element("Ladung");
		root.addContent(ladung);
		ladung.setText(String.valueOf(tD.getLadung()));
		
		//Document with attached root element
		Document doc = new Document(root);
		
		
		//FileOutPutStream with fileName given from Method
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat());
			serializer.output(doc, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println(e);
			e.fillInStackTrace();
		}
		
	}

}
