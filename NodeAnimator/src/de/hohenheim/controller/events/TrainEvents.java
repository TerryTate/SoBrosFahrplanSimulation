package de.hohenheim.controller.events;

import de.hohenheim.controller.main.Main;
import de.hohenheim.view.composite.CompositeTrain;


public class TrainEvents {
	
	public static void addNewTrain() {
		
		reloadTrainTable();
	}

	private static void reloadTrainTable() {
		
		CompositeTrain compositeTrain = (CompositeTrain) Main.cTabFolder.getItem(2).getControl();
		compositeTrain.loadTableEntry();
		
	}
	

}
