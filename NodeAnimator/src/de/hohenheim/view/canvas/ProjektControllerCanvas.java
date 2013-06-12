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
import de.hohenheim.controller.events.CentralEventController;
import de.hohenheim.controller.events.ProjectEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.composite.CompositeProject;


public class ProjektControllerCanvas extends Canvas {

	private Group groupControlSmall;

	public ProjektControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
	    
		// Group 
		
		groupControlSmall = new Group(this, SWT.SHADOW_ETCHED_IN);
	    groupControlSmall.setText("Projekt Verwaltung");
	    GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 1; 
        groupControlSmall.setLayout(gridLayout);
       
        // Add Button
	    
		Button newProject = new Button(groupControlSmall, SWT.NONE);
		newProject.setText("Projekt hinzufügen");
		newProject.setImage(new Image(null,"img/add.png"));
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		newProject.setLayoutData(gridData);
	
		newProject.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 CentralEventController.openAddDialog(2);
				
			}
		});
		
		 
		Button editProject = new Button(groupControlSmall, SWT.NONE);
		editProject.setText("Project ändern");
		editProject.setImage(new Image(null,"img/Edit.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		editProject.setLayoutData(gridData);
		
		editProject.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				if(Main.projectListAll.size() > 0){	
	                 boolean showText = false;
			    	 
			         for(int i = 0; i < Main.projectListAll.size(); i++){
			        	
			        	 if(CompositeProject.getProjectTable().isSelected(i)){
			        		 CentralEventController.openEditDialog(false, 2);
			        		 showText = true;
			        	 } 
			         }
			         
			         if(showText == false){
			        	 MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Sie haben keinen Projekt Ausgewählt ! \n Drücken Sie auf Ok und wählen Sie einen Fahrplan aus.");    
				         messageBox.open();
			         }
				   }else{
					     MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Es gibt keine Projekte die Bearbeitet werden können !");    
				         messageBox.open();
				   }
				 
			
				
			}
		});
		
		Button deleteProject = new Button(groupControlSmall, SWT.NONE);
		deleteProject.setText("Project löschen");
		deleteProject.setImage(new Image(null,"img/Delete.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteProject.setLayoutData(gridData);

		deleteProject.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				 
				if(Main.projectListAll.size() > 0){	
	                 boolean showText = false;
			    	 
			         for(int i = 0; i < Main.projectListAll.size(); i++){
			        	
			        	 if(CompositeProject.getProjectTable().isSelected(i)){
			        		 ProjectEvents.deletProject();
			        		 showText = true;
			        	 } 
			         }
			         
			         if(showText == false){
			        	 MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Sie haben keinen Projekt Ausgewählt ! \n Drücken Sie auf Ok und wählen Sie einen Fahrplan aus.");    
				         messageBox.open();
			         }
				   }else{
					     MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Es gibt keine Projekte die Bearbeitet werden können !");    
				         messageBox.open();
				   }
				 
				
			}
		});
		
		Button importProject = new Button(groupControlSmall, SWT.NONE);
		importProject.setText("Zug Importieren");
		importProject.setImage(new Image(null,"img/Import.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importProject.setLayoutData(gridData);
		
		importProject.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		Button exportProject = new Button(groupControlSmall, SWT.NONE);
		exportProject.setText("Zug Exportieren");
		exportProject.setImage(new Image(null,"img/export.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportProject.setLayoutData(gridData);
	
		exportProject.addListener(SWT.Selection, new Listener() {
					
		     public void handleEvent(Event arg0) {
						
		    	 if(Main.projectListAll.size() > 0){	
	                 boolean showText = false;
			    	 
			         for(int i = 0; i < Main.projectListAll.size(); i++){
			        	
			        	 if(CompositeProject.getProjectTable().isSelected(i)){
			        		 
			        		 showText = true;
			        	 } 
			         }
			         
			         if(showText == false){
			        	 MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Sie haben keinen Projekt Ausgewählt ! \n Drücken Sie auf Ok und wählen Sie einen Fahrplan aus.");    
				         messageBox.open();
			         }
				   }else{
					     MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Es gibt keine Projekte die Exportiert werden können !");    
				         messageBox.open();
				   }
						
			}
		});
		
		groupControlSmall.pack();
		
		
	}

}
