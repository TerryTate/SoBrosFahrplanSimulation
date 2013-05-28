package de.hohenheim.controller.events;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.canvas.TrainControllerCanvas;
import de.hohenheim.view.composite.CompositeTrain;



/**
 * Java-Class that contains methods to handle the train events, such as
 * add a new train to the list of trains, edit or remove it.
 * It also allows the user to import and export trains.
 * @author SoBros
 *
 */
public class TrainEvents {
	
	
	
	
	
	/**
	 * This method adds a new train to the ArrayList trainListAll in the Main class
	 * with the trains id, speed and typeOfTrain delivered by the controller canvas
	 */
	public static void addNewTrain() {
		
		/**
		 * String that contains the text written in the "ID text field" of the Canvas.
		 */
		String idText = TrainControllerCanvas.getTextID().getText();
		
		
		
		/**
		 * String that contains the text written in the "Speed text field" of the Canvas.
		 */
		String speedText = TrainControllerCanvas.getTextSpeed().getText();
		
		
		//Als Lokale Variablen deklarieren und dann des parsen als Integer mit Try/Catch umgeben!!
		int id = Integer.parseInt(idText);
		int speed = Integer.parseInt(speedText);
		
		
		
		TrainData train = new TrainData(id, speed, TrainControllerCanvas.getTypOfTrain_combo().getText());
		
		Main.trainListAll.add(train);
		
		TableItem item = new TableItem(CompositeTrain.getTrainTable(), SWT.NONE);
		item.setText(new String[]{String.valueOf(train.getID()),
				train.getTypOfTrain(),
				String.valueOf(train.getSpeed())});
		
	}
	
	
	
	
	
	
	/**
	 * This method provides the ability to chance single trains in in the ArrayList
	 * trainListAll of the Main class.
	 * By the id this methods checks whether it is the same train you want to reedit or not.
	 * 
	 */
	public static void editTrain() {
		
		TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
		
		String idText = TrainControllerCanvas.getTextID2().getText();
		int id = Integer.parseInt(idText);
		String speedText = TrainControllerCanvas.getTextSpeed2().getText();
		int speed = Integer.parseInt(speedText);
		
		int idCheck = Integer.parseInt(rowData[0].getText(0));
		
		for (TrainData td : Main.trainListAll){
			
			if (idCheck == td.getID()){
				td.setID(id);
				td.setSpeed(speed);
				td.setTypOfTrain(TrainControllerCanvas.getTypOfTrain_combo2().getText());
			}
		}
	    
		rowData[0].setText(0, idText);
		rowData[0].setText(1, TrainControllerCanvas.getTypOfTrain_combo2().getText());
		rowData[0].setText(2, speedText);
		
	}
	
	/**
	 * Method to delete single trains and remove them from the trainListAll in the 
	 * Main class.
	 */
	public static void deleteTrain() {
		

		int i = 0;
	
		TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
			
		while((rowData[0].getText(0)) == (String.valueOf(Main.trainListAll.get(i).getID()))){
			i++;
		}
		
		Main.trainListAll.remove(i);
		
		CompositeTrain.getTrainTable().remove(CompositeTrain.getTrainTable().getSelectionIndices());  
			

	}
	
}
