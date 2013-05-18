package de.hohenheim.controller.events;

import de.hohenheim.controller.main.Main;
import de.hohenheim.controller.main.TrainData;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.composite.TrainControllerCanvas;




public class TrainEvents {
	
	public static void addNewTrain() {
		
		String idText = TrainControllerCanvas.textID.getText();
		int id = Integer.parseInt(idText);
		String speedText = TrainControllerCanvas.textSpeed.getText();
		int speed = Integer.parseInt(speedText);
		TrainData train = new TrainData(id, speed, TrainControllerCanvas.textStartStation.getText());
		Main.trainList.add(train);
		reloadTrainTable();
	}

	private static void reloadTrainTable() {
		
		CompositeTrain compositeTrain = (CompositeTrain) Main.cTabFolder.getItem(2).getControl();
		compositeTrain.loadTableEntry();
		
	}
	
	
	

}
