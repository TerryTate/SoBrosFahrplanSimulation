package de.hohenheim.view.canvas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TrainEvents;

public class TimeTableControllerCanvas extends Canvas {

	private Group groupAddTimeTable;
	private Group groupEditTimeTable;
	private Group groupDeletTimeTable;
	private Group groupImportTimeTable;
	private Group groupExportTimeTable;
	private Group groupControlSmall;

	public TimeTableControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
		
		// Group with all controllers for add a new TimeTable
		
		groupAddTimeTable = new Group(this, SWT.SHADOW_ETCHED_IN);
		groupAddTimeTable.setText("Fahrplan hinzufügen");
		GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 3; 
        groupAddTimeTable.setLayout(gridLayout);
        
	   
		
			    
	    // Add Button
		    
		Button newTrain = new Button(groupAddTimeTable, SWT.NONE);
		newTrain.setText("Zug hinzufügen");
		GridData gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    gridData.horizontalSpan = 3; 
	    newTrain.setLayoutData(gridData);
				
		newTrain.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
				TrainEvents.addNewTrain();
						
			}
		});
				
		groupAddTimeTable.pack();
				
	    // Group with all controllers for edit a existing TimeTable
				
		groupEditTimeTable = new Group(this, SWT.SHADOW_ETCHED_IN );
		groupEditTimeTable.setText("Zug bearbeiten");
		groupEditTimeTable.setBounds(0, 135, 0, 0);
		groupEditTimeTable.setLayout(gridLayout);
		        
			       
			     
		Button edit = new Button(groupEditTimeTable, SWT.NONE);
		edit.setText("Fahrplan ändern");
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    gridData.horizontalSpan = 3; 
	    edit.setLayoutData(gridData);
				
		edit.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						

						
			}
		});
				
		groupEditTimeTable.pack();
				
		// Group with all controllers for delete a existing TimeTable
				
		groupDeletTimeTable = new Group(this, SWT.SHADOW_ETCHED_IN );
		groupDeletTimeTable.setText("Fahrplan löschen");
		groupDeletTimeTable.setBounds(0, 265, 0, 0);
		groupDeletTimeTable.setLayout(gridLayout);
				
		Button deleteTimeTable = new Button(groupDeletTimeTable, SWT.NONE);
		deleteTimeTable.setText("Zug löschen");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTimeTable.setLayoutData(gridData);
		deleteTimeTable.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		groupDeletTimeTable.pack();
				
		//Group with all controllers for import a TimeTable
				
		groupImportTimeTable = (new Group(this, SWT.SHADOW_ETCHED_IN ));
		groupImportTimeTable.setText("Fahrplan Importieren");
		groupImportTimeTable.setBounds(0, 317, 400, 0);
		groupImportTimeTable.setLayout(gridLayout);
				
		Button importTimeTable = new Button(groupImportTimeTable, SWT.NONE);
		importTimeTable.setText("Fahrplan Importieren");
		gridData = new GridData();
				
		importTimeTable.setLayoutData(gridData);
		importTimeTable.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		groupImportTimeTable.pack();
				
		//Group with all controllers for export a TimeTable
				
		groupExportTimeTable = new Group(this, SWT.SHADOW_ETCHED_IN );
		groupExportTimeTable.setText("Fahrplan Exportieren");
		groupExportTimeTable.setBounds(0, 370, 0, 0);
		groupExportTimeTable.setLayout(gridLayout);
				
		Button exportTrain = new Button(groupExportTimeTable, SWT.NONE);
		exportTrain.setText("Fahrplan Exportieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTrain.setLayoutData(gridData);
		exportTrain.addListener(SWT.Selection, new Listener() {
							
		    public void handleEvent(Event arg0) {
								
							
								
			}
		});
				
		groupExportTimeTable.pack();
				
		// Group when the shellHeight < then 300 pixel
				
		groupControlSmall = new Group(this, SWT.SHADOW_ETCHED_IN);
		groupControlSmall.setText("Zug Verwaltung");
		GridLayout gridLayout3 = new GridLayout(); 
        gridLayout.numColumns = 1; 
        groupControlSmall.setLayout(gridLayout3);
		        
		Button newTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		newTimeTable2.setText("Fahrplan hinzufügen");		
		newTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
				 
		Button editTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		editTimeTable2.setText("Fahrplan ändern");
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
		gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 3; 
		editTimeTable2.setLayoutData(gridData);
		editTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
				
						
			}
		});
				
		Button deleteTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		deleteTimeTable2.setText("Fahrplan löschen");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTimeTable2.setLayoutData(gridData);
		deleteTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		Button importTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		importTimeTable2.setText("Fahrplan Importieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importTimeTable2.setLayoutData(gridData);
		importTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		Button exportTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		exportTimeTable2.setText("Fahrplan Exportieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTimeTable2.setLayoutData(gridData);
		exportTimeTable2.addListener(SWT.Selection, new Listener() {
							
			public void handleEvent(Event arg0) {
								
							
								
			}
		});
				
		groupControlSmall.pack();
		groupControlSmall.setVisible(false);
		
	}

}
