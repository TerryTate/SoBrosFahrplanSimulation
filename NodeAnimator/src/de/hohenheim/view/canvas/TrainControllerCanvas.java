package de.hohenheim.view.canvas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import de.hohenheim.controller.events.MenuBarEvents;
import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.composite.CompositeTrain;

public class TrainControllerCanvas extends Canvas{
	
	String[] typOfTrain = {"S-Bahn", "ICE", "IC", "RegioBahn", "G�terzug", "Dampflock"};
	
	private static Group groupControlSmall;
	
	public TrainControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
	
		// Group 
		
		groupControlSmall = new Group(this, SWT.SHADOW_ETCHED_IN);
		groupControlSmall.setText("Zug Verwaltung");
		GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 1; 
        groupControlSmall.setLayout(gridLayout);
       
        // Add Button
        
		Button newTrain = new Button(groupControlSmall, SWT.NONE);
		newTrain.setText("Zug hinzuf�gen");
		newTrain.setImage(new Image(null,"img/add.png"));
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		newTrain.setLayoutData(gridData);
		newTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 MenuBarEvents.addTrain();
				
			}
		});
		
		// Edit Train Button
		
		Button editTrain = new Button(groupControlSmall, SWT.NONE);
		editTrain.setText("Zug bearbeiten");
		editTrain.setImage(new Image(null,"img/Edit.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		editTrain.setLayoutData(gridData);
		editTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 MenuBarEvents.editTrain(false);
				
			}
		});
		
		// Delete Train Button
		
		Button deleteTrain = new Button(groupControlSmall, SWT.NONE);
		deleteTrain.setText("Zug l�schen");
		deleteTrain.setImage(new Image(null,"img/Delete.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTrain.setLayoutData(gridData);
		deleteTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 if(Main.trainListAll.size() < 1){
				        MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
					    messageBox.setMessage("Es existieren keine Z�ge die gel�scht werden k�nnen.");    
				        messageBox.open();
			     }
			         
			           
			    for(int i = 0; i < Main.trainListAll.size(); i++){
			        	
			        if(CompositeTrain.getTrainTable().isSelected(i)){
			            TrainEvents.deleteTrain(false);
			        } 
			    }    
			    
			   
			}
		});
		
		// Import Train Button
		
		Button importTrain = new Button(groupControlSmall, SWT.NONE);
		importTrain.setText("Zug Importieren");
		importTrain.setImage(new Image(null,"img/Import.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importTrain.setLayoutData(gridData);
		importTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    
				
			}
		});
		
		// Export Train Button
		
		Button exportTrain = new Button(groupControlSmall, SWT.NONE);
		exportTrain.setText("Zug Exportieren");
		exportTrain.setImage(new Image(null,"img/export.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTrain.setLayoutData(gridData);
		exportTrain.addListener(SWT.Selection, new Listener() {
					
		     public void handleEvent(Event arg0) {
				 
		    	 boolean showText = false;
		    	 
		         for(int i = 0; i < Main.trainListAll.size(); i++){
		        	
		        	 if(CompositeTrain.getTrainTable().isSelected(i)){
		        		 MenuBarEvents.save(false);
		        		 showText = true;
		        	 } 
		         }
		         
		         if(showText == false){
		        	 MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
			         messageBox.setMessage("Sie haben keinen Zug Ausgew�hlt ! \n Dr�cken Sie auf Ok und w�hlen Sie einen Zug aus.");    
			         messageBox.open();
		         }
		         
			}
		});
		
		groupControlSmall.pack();
		
	}
		
}
