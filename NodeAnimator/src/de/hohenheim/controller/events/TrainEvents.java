package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.canvas.TrainControllerCanvas;
import de.hohenheim.view.composite.CompositeTrain;




public class TrainEvents {
	
	public static void addNewTrain() {
		
		String idText = TrainControllerCanvas.getTextID().getText();
		int id = Integer.parseInt(idText);
		String speedText = TrainControllerCanvas.getTextSpeed().getText();
		int speed = Integer.parseInt(speedText);
		TrainData train = new TrainData(id, speed, TrainControllerCanvas.getTypOfTrain_combo().getText());
		
		Main.trainListAll.add(train);
		
		TableItem item = new TableItem(CompositeTrain.getTrainTable(), SWT.NONE);
		item.setText(new String[]{String.valueOf(train.getID()),
				train.gettypOfTrain(),
				String.valueOf(train.getSpeed())});
		
	}
	
	public static void editTrain() {
	 
	}
}
