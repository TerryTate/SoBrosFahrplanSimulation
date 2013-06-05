package de.hohenheim.view.canvas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;

import de.hohenheim.controller.events.MenuBarEvents;
import de.hohenheim.controller.events.TrainEvents;

public class ProjektControllerCanvas extends Canvas {

	private Group groupControlSmall;

	public ProjektControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
	    
		// Group 
		
		groupControlSmall = new Group(this, SWT.SHADOW_ETCHED_IN);
	    groupControlSmall.setText("Zug Verwaltung");
	    groupControlSmall.setLayout(new FillLayout(SWT.VERTICAL));
       
        // Add Button
	    
		Button newProject = new Button(groupControlSmall, SWT.NONE);
		newProject.setText("Projekt hinzuf�gen");
	
		newProject.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 
				
			}
		});
		
		 
		Button editProject = new Button(groupControlSmall, SWT.NONE);
		editProject.setText("Project �ndern");
		
		editProject.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 
				
			}
		});
		
		Button deleteProject = new Button(groupControlSmall, SWT.NONE);
		deleteProject.setText("Project l�schen");

		deleteProject.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 
				
			}
		});
		
		Button importTrain = new Button(groupControlSmall, SWT.NONE);
		importTrain.setText("Zug Importieren");
		
		importTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		Button exportTrain = new Button(groupControlSmall, SWT.NONE);
		exportTrain.setText("Zug Exportieren");
	
		exportTrain.addListener(SWT.Selection, new Listener() {
					
		     public void handleEvent(Event arg0) {
						
					
						
			}
		});
		
		groupControlSmall.pack();
		
		
	}

}
