package de.hohenheim.view.canvas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import de.hohenheim.controller.events.CentralEventController;
import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * This class is responsible for the GUI of the TrainControllerCanvas.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs��
 * 
 * @version 1.0
 */
public class TrainControllerCanvas extends Canvas{
	
	private static Group groupControlSmall;
	
	/**
	 * A Constructor for a new TrainControllerCanvas.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public TrainControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	/**
	 * Create the content of the new TrainControllerCanvas
	 */
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
		newTrain.setImage(ImageHelper.add);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		newTrain.setLayoutData(gridData);
		newTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 CentralEventController.openAddDialog(0);
				
			}
		});
		
		// Edit Train Button
		
		Button editTrain = new Button(groupControlSmall, SWT.NONE);
		editTrain.setText("Zug bearbeiten");
		editTrain.setImage(ImageHelper.editF);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		editTrain.setLayoutData(gridData);
		editTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
                 if(Main.getTrainListAll().size() > 0){
					 
		    	     boolean showText = false;
		    	 
		             for(int i = 0; i < Main.getTrainListAll().size(); i++){
		        	
		        	     if(CompositeTrain.getTrainTable().isSelected(i)){
		        		     CentralEventController.openEditDialog(false, 0);
		        		     showText = true;
		        	     } 
		             }
		         
		             if(showText == false){
		        	     MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
			             messageBox.setMessage("Sie haben keinen Zug gew�hlt!" + "\r\n" + "\r\n" + 
	        		 			   			   "W�hlen Sie einen Zug aus und dr�cken Sie auf Ok.");messageBox.open();
		             }
				 }else{
					 MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
		             messageBox.setMessage("Es sind keine Z�ge vorhanden, die bearbeitet werden k�nnen!");    
		             messageBox.open();
				 }
				
			}
		});
		
		// Delete Train Button
		
		Button deleteTrain = new Button(groupControlSmall, SWT.NONE);
		deleteTrain.setText("Zug l�schen");
		deleteTrain.setImage(ImageHelper.delete);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTrain.setLayoutData(gridData);
		deleteTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				if(Main.getTrainListAll().size() > 0){
					 
		    	     boolean showText = false;
		    	 
		             for(int i = 0; i < Main.getTrainListAll().size(); i++){
		        	
		        	     if(CompositeTrain.getTrainTable().isSelected(i)){
		        		     TrainEvents.deleteTrain(false);
		        		     showText = true;
		        	     } 
		             }
		         
		             if(showText == false){
		        	     MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
			             messageBox.setMessage("Sie haben keinen Zug gew�hlt!" + "\r\n" + "\r\n" + 
	        		 			   			   "W�hlen Sie einen Zug aus und dr�cken Sie auf Ok."); 
			             messageBox.open();
		             }
				 }else{
					 MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
		             messageBox.setMessage("Es sind keine Z�ge vorhanden, die gel�scht werden k�nnen!");    
		             messageBox.open();
				 }  
			    
			   
			}
		});
		
		// Import Train Button
		
		Button importTrain = new Button(groupControlSmall, SWT.NONE);
		importTrain.setText("Zug Importieren");
		importTrain.setImage(ImageHelper.importPic);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importTrain.setLayoutData(gridData);
		importTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    CentralEventController.open(0);
				
			}
		});
		
		// Export Train Button
		
		Button exportTrain = new Button(groupControlSmall, SWT.NONE);
		exportTrain.setText("Zug Exportieren");
		exportTrain.setImage(ImageHelper.export);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTrain.setLayoutData(gridData);
		exportTrain.addListener(SWT.Selection, new Listener() {
					
		     public void handleEvent(Event arg0) {
				 if(Main.getTrainListAll().size() > 0){
					 
		    	     boolean showText = false;
		    	 
		             for(int i = 0; i < Main.getTrainListAll().size(); i++){
		        	
		        	     if(CompositeTrain.getTrainTable().isSelected(i)){
		        		     CentralEventController.save(false, 0);
		        		     showText = true;
		        	     } 
		             }
		         
		             if(showText == false){
		        	     MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
			             messageBox.setMessage("Sie haben keinen Zug gew�hlt!" + "\r\n" + "\r\n" + 
	        		 			   			   "W�hlen Sie einen Zug aus und dr�cken Sie auf Ok."); 
			             messageBox.open();
		             }
				 }else{
					 MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
		             messageBox.setMessage("Es sind keine Z�ge vorhanden, die exportiert werden k�nnen!");    
		             messageBox.open();
				 }
				 
			}
		});
		
		groupControlSmall.pack();
		
	}
		
}
