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
import de.hohenheim.controller.events.ProjectEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.composite.CompositeProject;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * 
 * @author Arthur Kaul
 *
 */
public class ProjektControllerCanvas extends Canvas {

	private Group groupControlSmall;

	/**
	 * 
	 * @param parent
	 * @param style
	 */
	public ProjektControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	/**
	 * 
	 * 
	 */
	private void createContent() {
	    
		// Group 
		
		groupControlSmall = new Group(this, SWT.SHADOW_ETCHED_IN);
	    groupControlSmall.setText("Projekt Verwaltung");
	    GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 1; 
        groupControlSmall.setLayout(gridLayout);
       
        // Add Button
	    
		Button newProject = new Button(groupControlSmall, SWT.NONE);
		newProject.setText("Projekt hinzuf�gen");
		newProject.setImage(ImageHelper.add);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		newProject.setLayoutData(gridData);
	
		newProject.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 CentralEventController.openAddDialog(2);
				
			}
		});
		
		 
		Button editProject = new Button(groupControlSmall, SWT.NONE);
		editProject.setText("Project �ndern");
		editProject.setImage(ImageHelper.editF);
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
				         messageBox.setMessage("Sie haben kein Projekt gew�hlt!" + "\r\n" + "\r\n" + 
				        		 			   "W�hlen Sie einen Projekt aus und dr�cken Sie auf Ok.");   
				         messageBox.open();
			         }
				   }else{
					     MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Es sind keine Projekte vorhanden, die barbeitet werden k�nnen!");    
					          messageBox.open();
				   }
				 
			
				
			}
		});
		
		Button deleteProject = new Button(groupControlSmall, SWT.NONE);
		deleteProject.setText("Project l�schen");
		deleteProject.setImage(ImageHelper.delete);
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
				         messageBox.setMessage("Sie haben kein Projekt gew�hlt!" + "\r\n" + "\r\n" + 
				        		 			   "W�hlen Sie einen Projekt aus und dr�cken Sie auf Ok.");    
					         messageBox.open();
			         }
				   }else{
					     MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Es sind keine Projekte vorhanden, die gel�scht werden k�nnen!");    
				         messageBox.open();
				   }
				 
				
			}
		});
		
		Button importProject = new Button(groupControlSmall, SWT.NONE);
		importProject.setText("Projekt importieren");
		importProject.setImage(ImageHelper.importPic);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importProject.setLayoutData(gridData);
		
		importProject.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    CentralEventController.open(2);
				
			}
		});
		
		Button exportProject = new Button(groupControlSmall, SWT.NONE);
		exportProject.setText("Projekt exportieren");
		exportProject.setImage(ImageHelper.export);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportProject.setLayoutData(gridData);
	
		exportProject.addListener(SWT.Selection, new Listener() {
					
		     public void handleEvent(Event arg0) {
						
		    	 if(Main.projectListAll.size() > 0){	
	                 boolean showText = false;
			    	 
			         for(int i = 0; i < Main.projectListAll.size(); i++){
			        	
			        	 if(CompositeProject.getProjectTable().isSelected(i)){
			        		 CentralEventController.save(false, 2); 
			        		 showText = true;
			        	 } 
			         }
			         
			         if(showText == false){
			        	 MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Sie haben kein Projekt gew�hlt!" + "\r\n" + "\r\n" + 
				        		 			   "W�hlen Sie einen Projekt aus und dr�cken Sie auf Ok.");    
				         messageBox.open();
			         }
				   }else{
					     MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
				         messageBox.setMessage("Es sind keine Projekte vorhanden, die exportiert werden k�nnen!");    
				         messageBox.open();
				   }
						
			}
		});
		
		groupControlSmall.pack();
		
		
	}

}
