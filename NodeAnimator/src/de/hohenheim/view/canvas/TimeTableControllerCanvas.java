package de.hohenheim.view.canvas;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.MenuBarEvents;
import de.hohenheim.controller.events.TimeTableEvents;


public class TimeTableControllerCanvas extends Canvas {
	
    
	private static Group groupControlSmall;
	
	public TimeTableControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
			
		// Group when the shellHeight < then 300 pixel
				
		groupControlSmall = (new Group(this, SWT.SHADOW_ETCHED_IN));
		groupControlSmall.setText("Zug Verwaltung");
		GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 1; 
        groupControlSmall.setLayout(gridLayout);
		        
		Button newTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		newTimeTable2.setText("Fahrplan hinzufügen");	
		newTimeTable2.setImage(new Image(null,"img/add.png"));
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		newTimeTable2.setLayoutData(gridData);
		newTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
			    MenuBarEvents.addTimeTable();		
						
			}
		});
				
				 
		Button editTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		editTimeTable2.setText("Fahrplan bearbeiten");
		editTimeTable2.setImage(new Image(null,"img/Edit.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		editTimeTable2.setLayoutData(gridData);
		editTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
				 MenuBarEvents.edtitTimetable(false);	
						
			}
		});
				
		Button deleteTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		deleteTimeTable2.setText("Fahrplan löschen");
		deleteTimeTable2.setImage(new Image(null,"img/Delete.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTimeTable2.setLayoutData(gridData);
		deleteTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
				TimeTableEvents.deleteTimeTable(false);		
						
			}
		});
				
		Button importTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		importTimeTable2.setText("Fahrplan Importieren");
		importTimeTable2.setImage(new Image(null,"img/Import.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importTimeTable2.setLayoutData(gridData);
		importTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		Button exportTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		exportTimeTable2.setText("Fahrplan Exportieren");
		exportTimeTable2.setImage(new Image(null,"img/export.png"));
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTimeTable2.setLayoutData(gridData);
		exportTimeTable2.addListener(SWT.Selection, new Listener() {
							
			public void handleEvent(Event arg0) {
								
							
								
			}
		});
				
		groupControlSmall.pack();
		
		
	}


}
