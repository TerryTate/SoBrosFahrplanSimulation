package de.hohenheim.controller;


import java.io.FileOutputStream;

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

	// Methode muss static sein damit man kein XMLWriter Objekt
	/**
	 * Static method to write XML-file that provides the trains and time tables
	 * (train schedules) we want to save. Is static so you don't have to
	 * instantiate the XmlWriter as object to use this method.
	 * 
	 * (Note: DOM = Document Object Model)
	 * 
	 * @param fileName
	 *            Name of the saved XML-File as String, importent to distinguish
	 *            the different trains and time tables.
	 */
	public static void writeToXML(String fileName) {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			/*
			 * DOM Document
			 */
			Document document = builder.newDocument();

			/*
			 * Root Element of DOM-Tree 
			 * 
			 * (@Arthur, falls das hier nicht klappt kanns
			 * daran liegen das dies Element-type schwierigkeiten macht)
			 */
			org.w3c.dom.Element root = document.createElement("TrainSimultion ");
			
			
			/*
			 * TRAINS
			 * ------
			 * Node elements get attached to the root element
			 * Root -> AllTrains -> Train -> ID -> TypeOfTrain -> etc.
			 * 
			 * Every "smaller" element gets attached as child node
			 */
			
			org.w3c.dom.Element allTrains = document.createElement("Trains_Overview");
			root.appendChild(allTrains);
			
			//loop for all trains
			for (TrainData tD : Main.trainListAll) {
				
				Element train = document.createElement("Train");
				allTrains.appendChild(train);
				
				Element iD = document.createElement("ID");
				train.appendChild(iD);
				iD.setTextContent(String.valueOf(tD.getID()));
				//iD.setTextContent(tD.getID().getString);
				
				Element trainType = document.createElement("TrainType");
				train.appendChild(trainType);
				trainType.setTextContent(String.valueOf(tD.getTypOfTrain()));
				
				Element priority = document.createElement("Priority");
				train.appendChild(priority);
				priority.setTextContent(String.valueOf(tD.getPriority()));
				
				Element speed = document.createElement("Speed");
				train.appendChild(speed);
				speed.setTextContent(String.valueOf(tD.getSpeed()));
				
				Element payLoad = document.createElement("Payload");
				train.appendChild(payLoad);
				payLoad.setTextContent(String.valueOf(tD.getLadung()));
				
				
				
				
				
				FileOutputStream out = new FileOutputStream(fileName);
				
				//@Arthur Funktioniert anscheinend so, XML Output Factory hab ich auch probiert...kp
				
				XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat());
				serializer.output(document, out);
				out.flush();
				out.close();
				
			}
			
		
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		
		
	}
}
