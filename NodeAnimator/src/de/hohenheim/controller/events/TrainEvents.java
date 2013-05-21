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
				train.getTypOfTrain(),
				String.valueOf(train.getSpeed())});
		
	}
	
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
	
	public static void deleteTrain(){
		
	}
	
}
