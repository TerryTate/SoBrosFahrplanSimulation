package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.dialouge.TrainAddDialog;
import de.hohenheim.view.dialouge.TrainDeletDialog;
import de.hohenheim.view.dialouge.TrainEditDialog;

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
		String idText = TrainAddDialog.idText.getText();
			
		/**
		 * String that contains the text written in the "Speed text field" of the Canvas.
		 */
		String speedText = TrainAddDialog.comboSpeed.getText();
		
		int id = Integer.parseInt(idText);
		
		int speed = Integer.parseInt(speedText);
		
		TrainData train = new TrainData(id, speed, TrainAddDialog.comboTypOfTrain.getText(), TrainAddDialog.comboLadungen.getText(), TrainAddDialog.comboPriority.getText() );
		
		Main.trainListAll.add(train);
		
		TableItem item = new TableItem(CompositeTrain.getTrainTable(), SWT.NONE);
		item.setText(new String[]{String.valueOf(train.getID()),
				train.getTypOfTrain(),
				String.valueOf(train.getSpeed()), train.getPriority(), train.getLadung()});
		
		TrainAddDialog.dialog.close();
		
	}
	
	
	/**
	 * This method provides the ability to chance single trains in in the ArrayList
	 * trainListAll of the Main class.
	 * By the id this methods checks whether it is the same train you want to reedit or not.
	 * 
	 */
	public static void editTrain(boolean menu) {
		
		if (menu == false){
		
			TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
		
	    	String idText = TrainEditDialog.idText.getText();
		    int id = Integer.parseInt(idText);
		
		    String speedText = TrainEditDialog.comboSpeed.getText();
		    int speed = Integer.parseInt(speedText);
		
	    	int idCheck = Integer.parseInt(rowData[0].getText(0));
		
		    for (TrainData td : Main.trainListAll){
			
			    if (idCheck == td.getID()){
				    td.setID(id);
				    td.setSpeed(speed);
				    td.setTypOfTrain(TrainEditDialog.comboTypOfTrain.getText());
				    td.setLadung(TrainEditDialog.comboLadungen.getText());
				    td.setPriority(TrainEditDialog.comboPriority.getText());
		    	}
		    }
	    
		    rowData[0].setText(0, idText);
		    rowData[0].setText(1, TrainEditDialog.comboTypOfTrain.getText());
	 	    rowData[0].setText(2, speedText);
	 	    rowData[0].setText(3, TrainEditDialog.comboPriority.getText());
	 	    rowData[0].setText(4, TrainEditDialog.comboLadungen.getText());
	 	    
	 	   TrainEditDialog.dialog.close();
		
		}else if (menu == true){
				
			 for (TrainData td : Main.trainListAll){
			
			    if (Integer.parseInt(TrainEditDialog.comboTrains.getText()) == td.getID()){
				  
			    	td.setID(Integer.parseInt(TrainEditDialog.idText.getText()));
				    td.setSpeed(Integer.parseInt(TrainEditDialog.comboSpeed.getText()));
				    td.setTypOfTrain(TrainEditDialog.comboTypOfTrain.getText());
				    td.setLadung(TrainEditDialog.comboLadungen.getText());
				    td.setPriority(TrainEditDialog.comboPriority.getText());
				    
		    	}
		    }
			  
		    TableItem [] items = CompositeTrain.getTrainTable().getItems();
		   	TableItem item = items[0]; 
		   	int i = 0;
		   	
		   	while( Integer.parseInt(TrainEditDialog.comboTrains.getText()) != Integer.parseInt(item.getText(i))){
		   		i++;
		   		item = items[i];	
		   	}
			
		   	item.setText(0, TrainEditDialog.idText.getText());
		   	item.setText(1, TrainEditDialog.comboTypOfTrain.getText());
		   	item.setText(2, TrainEditDialog.comboSpeed.getText());
		   	item.setText(3, TrainEditDialog.comboPriority.getText());
		   	item.setText(4, TrainEditDialog.comboLadungen.getText());
			     
	 	    TrainEditDialog.dialog.close();
			
		}
		
	}
	
	/**
	 * Method to delete single trains and remove them from the trainListAll in the 
	 * Main class.
	 * @param menu 
	 */
	public static void deleteTrain(boolean menu) {
		
        if (menu == false){
		
        	int i = 0;
	
    		TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
			
	    	while(Integer.parseInt((rowData[0].getText(0))) != ((Main.trainListAll.get(i).getID()))){
		    	i++;
	    	}
		
		    Main.trainListAll.remove(i);
		
	    	CompositeTrain.getTrainTable().remove(CompositeTrain.getTrainTable().getSelectionIndices());  
			
	    }else if(menu == true){
	    	
	    	int i = 0; 
	    	
	    	TableItem [] items = CompositeTrain.getTrainTable().getItems();
	    	TableItem item = items[0]; 
	    	
	    	while(Integer.parseInt(TrainDeletDialog.comboTrains.getText()) != ((Main.trainListAll.get(i).getID()))){
		    	i++;
	    	}
	    	
	    	Main.trainListAll.remove(i);
	    	
	    	int j = 0; 
	    	
	    	while(Integer.parseInt(TrainDeletDialog.comboTrains.getText()) != Integer.parseInt(item.getText(0))){
		    	
	    		j++;
	    		item = items[j];
	    	}
	    	
	    	CompositeTrain.getTrainTable().remove(j);
	    	
	    	TrainDeletDialog.dialog.close();
	    	
	    }
	}
	
}
