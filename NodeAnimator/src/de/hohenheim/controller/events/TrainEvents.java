package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.controller.main.Main;
import de.hohenheim.controller.main.TrainData;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.composite.TrainControllerCanvas;




public class TrainEvents {
	
	public static void addNewTrain() {
		
		String idText = TrainControllerCanvas.getTextID().getText();
		int id = Integer.parseInt(idText);
		String speedText = TrainControllerCanvas.getTextSpeed().getText();
		int speed = Integer.parseInt(speedText);
		TrainData train = new TrainData(id, speed, TrainControllerCanvas.getTextStartStation().getText());
		
		Main.trainListAll.add(train);
		
		TableItem item = new TableItem(CompositeTrain.getTrainTable(), SWT.NONE);
		item.setText(new String[]{String.valueOf(train.getID()),
				String.valueOf(train.getSpeed()),
				train.getStartStation()});
		
	}

}
