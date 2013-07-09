package de.hohenheim.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;

/**
 * This class provides method/s to save trains and time tables (train schedules)
 * using XML-files.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class XmlWriter {

	private static Element drivingdays;

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

		Element rootSingleTrain = new Element("Train");

		Element id = new Element("ID");
		rootSingleTrain.addContent(id);
		id.setText(String.valueOf(tD.getID()));

		Element typeOfTrain = new Element("TypeOfTrain");
		rootSingleTrain.addContent(typeOfTrain);
		typeOfTrain.setText(String.valueOf(tD.getTypOfTrain()));

		Element priority = new Element("Priority");
		rootSingleTrain.addContent(priority);
		priority.setText(String.valueOf(tD.getPriority()));

		Element speed = new Element("Speed");
		rootSingleTrain.addContent(speed);
		speed.setText(String.valueOf(tD.getSpeed()));

		Element ladung = new Element("Ladung");
		rootSingleTrain.addContent(ladung);
		ladung.setText(String.valueOf(tD.getLadung()));

		// Document with attached root element
		Document doc = new Document(rootSingleTrain);

		// FileOutPutStream with fileName given from Method
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat());
			serializer.output(doc, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println(e);
			e.fillInStackTrace();

		} catch (NullPointerException e) {

		}

	}

	/**
	 * 
	 * @param fileName
	 * @param tD
	 */
	public static void saveSingleTimeTable(String fileName, Timetable tD) {

		Element rootTimeTable = new Element("Train");

		Element iD = new Element("ID");
		rootTimeTable.addContent(iD);
		iD.setText(String.valueOf(tD.getId()));

		Element name = new Element("Name");
		rootTimeTable.addContent(name);
		name.setText(String.valueOf(tD.getName()));

		for (int i = 0; i < tD.getDrivingdays().size() - 1; i++) {
			drivingdays = new Element("DrivingDay" + (i + 1));
			rootTimeTable.addContent(drivingdays);
			drivingdays.setText(String.valueOf(tD.getDrivingdays().get(i)));
		}

		Element rootStations = new Element("Stations");
		rootTimeTable.addContent(rootStations);

		Element startstation = new Element("StartStation");
		rootStations.addContent(startstation);
		startstation.setText(String.valueOf(tD.getStartstation()));

		Element middlestations = new Element("Middlestations");
		rootStations.addContent(middlestations);

		for (int i = 0; i < tD.getMiddlestations().size(); i++) {
			Element middlestation = new Element("MiddleStation");
			middlestations.addContent(middlestation);
			middlestation
					.setText(String.valueOf(tD.getMiddlestations().get(i)));
		}

		Element endstation = new Element("EndStation");
		rootStations.addContent(endstation);
		endstation.setText(String.valueOf(tD.getEndstation()));

		Element rootTime = new Element("Time");
		rootTimeTable.addContent(rootTime);

		Element startHouer = new Element("StartHouer");
		rootTime.addContent(startHouer);
		startHouer.setText(String.valueOf(tD.getStartHouer()));

		Element startMinutes = new Element("StarMinutes");
		rootTime.addContent(startMinutes);
		startMinutes.setText(String.valueOf(tD.getStartMinutes()));

		// Document with attached root element
		Document docTimeTable = new Document(rootTimeTable);

		// FileOutPutStream with fileName given from Method
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat());
			serializer.output(docTimeTable, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println(e);
			e.fillInStackTrace();
		} catch (NullPointerException e) {

		}

	}

	/**
	 * Method to save single Project into a XML-File using JDOM2
	 * 
	 * @param fileName
	 *            Will be the name of the XML-File that is made
	 */

	public static void saveSingleProject(String fileName, Project pR) {

		// Erste Zeilen nur String und Interger vom Fahrplan

		Element root = new Element("Project");

		Element iD = new Element("ID");
		root.addContent(iD);
		iD.setText(String.valueOf(pR.getId()));

		Element name = new Element("Name");
		root.addContent(name);
		name.setText(pR.getName());

		Element rootSingleTrain = new Element("Trains");
		root.addContent(rootSingleTrain);

		// Alle Z¸ge die in dem ZugArray sind

		for (int i = 0; i < pR.getTraindataProjectList().size(); i++) {

			Element train = new Element("Train");
			rootSingleTrain.addContent(train);

			Element id = new Element("ID");
			train.addContent(id);
			id.setText(String.valueOf(pR.getTraindataProjectList().get(i)
					.getID()));

			Element typeOfTrain = new Element("TypeOfTrain");
			train.addContent(typeOfTrain);
			typeOfTrain.setText(String.valueOf(pR.getTraindataProjectList()
					.get(i).getTypOfTrain()));

			Element priority = new Element("Priority");
			train.addContent(priority);
			priority.setText(String.valueOf(pR.getTraindataProjectList().get(i)
					.getPriority()));

			Element speed = new Element("Speed");
			train.addContent(speed);
			speed.setText(String.valueOf(pR.getTraindataProjectList().get(i)
					.getSpeed()));

			Element ladung = new Element("Ladung");
			train.addContent(ladung);
			ladung.setText(String.valueOf(pR.getTraindataProjectList().get(i)
					.getLadung()));

		}

		// Alle Fahrpl‰ne die in dem TimeTableArray sind

		Element rootTimeTable = new Element("TimeTables");
		root.addContent(rootTimeTable);

		for (int i = 0; i < pR.getTimeTableProjectList().size(); i++) {

			Element timeTable = new Element("TimeTable");
			rootTimeTable.addContent(timeTable);

			Element iDTimeTable = new Element("ID");
			timeTable.addContent(iDTimeTable);
			iDTimeTable.setText(String.valueOf(pR.getTimeTableProjectList()
					.get(i).getId()));

			Element nameTimeTable = new Element("Name");
			timeTable.addContent(nameTimeTable);
			nameTimeTable.setText(String.valueOf(pR.getTimeTableProjectList()
					.get(i).getName()));

			for (int y = 0; y < pR.getTimeTableProjectList().get(i)
					.getDrivingdays().size(); y++) {
				drivingdays = new Element("DrivingDay" + (y + 1));
				timeTable.addContent(drivingdays);
				drivingdays.setText(String.valueOf(pR.getTimeTableProjectList()
						.get(i).getDrivingdays().get(y)));
			}

			Element rootStations = new Element("Stations");
			timeTable.addContent(rootStations);

			Element startstation = new Element("StartStation");
			rootStations.addContent(startstation);
			startstation.setText(String.valueOf(pR.getTimeTableProjectList()
					.get(i).getStartstation()));

			Element allMiddleStations = new Element("AllMiddleStations");
			rootStations.addContent(allMiddleStations);

			for (int z = 0; z < pR.getTimeTableProjectList().get(i)
					.getMiddlestations().size(); z++) {
				Element middlestations = new Element("MiddleStation");
				allMiddleStations.addContent(middlestations);
				middlestations.setText(String.valueOf(String.valueOf(pR
						.getTimeTableProjectList().get(i).getMiddlestations()
						.get(z))));
			}

			Element endstation = new Element("EndStation");
			rootStations.addContent(endstation);
			endstation.setText(String.valueOf(pR.getTimeTableProjectList()
					.get(i).getEndstation()));

			Element rootTime = new Element("Time");
			timeTable.addContent(rootTime);

			Element startHouer = new Element("StartHouer");
			rootTime.addContent(startHouer);
			startHouer.setText(String.valueOf(pR.getTimeTableProjectList()
					.get(i).getStartHouer()));

			Element startMinutes = new Element("StarMinutes");
			rootTime.addContent(startMinutes);
			startMinutes.setText(String.valueOf(pR.getTimeTableProjectList()
					.get(i).getStartMinutes()));
		}

		Document doc = new Document(root);

		// FileOutPutStream with fileName given from Method
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat());
			serializer.output(doc, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println(e);
			e.fillInStackTrace();
		} catch (NullPointerException e) {

		}

	}

}
