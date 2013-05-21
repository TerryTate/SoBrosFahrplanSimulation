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
import de.hohenheim.controller.events.TrainEvents;

public class TimeTableControllerCanvas extends Canvas {

	private static Group groupAddTimeTable;
	private static Group groupEditTimeTable;
	private static Group groupDeletTimeTable;
	private static Group groupImportTimeTable;
	private static Group groupExportTimeTable;
	private static Group groupControlSmall;

	public TimeTableControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
		
		// Group with all controllers for add a new TimeTable
		
		setGroupAddTimeTable(new Group(this, SWT.SHADOW_ETCHED_IN));
		getGroupAddTimeTable().setText("Fahrplan hinzufügen");
		GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 3; 
        getGroupAddTimeTable().setLayout(gridLayout);
        
	   
		
			    
	    // Add Button
		    
		Button newTrain = new Button(getGroupAddTimeTable(), SWT.NONE);
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
				
		getGroupAddTimeTable().pack();
				
	    // Group with all controllers for edit a existing TimeTable
				
		setGroupEditTimeTable(new Group(this, SWT.SHADOW_ETCHED_IN ));
		getGroupEditTimeTable().setText("Zug bearbeiten");
		getGroupEditTimeTable().setBounds(0, 135, 0, 0);
		getGroupEditTimeTable().setLayout(gridLayout);
		        
			       
			     
		Button edit = new Button(getGroupEditTimeTable(), SWT.NONE);
		edit.setText("Fahrplan ändern");
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    gridData.horizontalSpan = 3; 
	    edit.setLayoutData(gridData);
				
		edit.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						

						
			}
		});
				
		getGroupEditTimeTable().pack();
				
		// Group with all controllers for delete a existing TimeTable
				
		setGroupDeletTimeTable(new Group(this, SWT.SHADOW_ETCHED_IN ));
		getGroupDeletTimeTable().setText("Fahrplan löschen");
		getGroupDeletTimeTable().setBounds(0, 265, 0, 0);
		getGroupDeletTimeTable().setLayout(gridLayout);
				
		Button deleteTimeTable = new Button(getGroupDeletTimeTable(), SWT.NONE);
		deleteTimeTable.setText("Zug löschen");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTimeTable.setLayoutData(gridData);
		deleteTimeTable.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		getGroupDeletTimeTable().pack();
				
		//Group with all controllers for import a TimeTable
				
		setGroupImportTimeTable((new Group(this, SWT.SHADOW_ETCHED_IN )));
		getGroupImportTimeTable().setText("Fahrplan Importieren");
		getGroupImportTimeTable().setBounds(0, 317, 400, 0);
		getGroupImportTimeTable().setLayout(gridLayout);
				
		Button importTimeTable = new Button(getGroupImportTimeTable(), SWT.NONE);
		importTimeTable.setText("Fahrplan Importieren");
		gridData = new GridData();
				
		importTimeTable.setLayoutData(gridData);
		importTimeTable.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		getGroupImportTimeTable().pack();
				
		//Group with all controllers for export a TimeTable
				
		setGroupExportTimeTable(new Group(this, SWT.SHADOW_ETCHED_IN ));
		getGroupExportTimeTable().setText("Fahrplan Exportieren");
		getGroupExportTimeTable().setBounds(0, 370, 0, 0);
		getGroupExportTimeTable().setLayout(gridLayout);
				
		Button exportTrain = new Button(getGroupExportTimeTable(), SWT.NONE);
		exportTrain.setText("Fahrplan Exportieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTrain.setLayoutData(gridData);
		exportTrain.addListener(SWT.Selection, new Listener() {
							
		    public void handleEvent(Event arg0) {
								
							
								
			}
		});
				
		getGroupExportTimeTable().pack();
				
		// Group when the shellHeight < then 300 pixel
				
		setGroupControlSmall(new Group(this, SWT.SHADOW_ETCHED_IN));
		getGroupControlSmall().setText("Zug Verwaltung");
		GridLayout gridLayout3 = new GridLayout(); 
        gridLayout.numColumns = 1; 
        getGroupControlSmall().setLayout(gridLayout3);
		        
		Button newTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
		newTimeTable2.setText("Fahrplan hinzufügen");		
		newTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
				 
		Button editTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
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
				
		Button deleteTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
		deleteTimeTable2.setText("Fahrplan löschen");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTimeTable2.setLayoutData(gridData);
		deleteTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		Button importTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
		importTimeTable2.setText("Fahrplan Importieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importTimeTable2.setLayoutData(gridData);
		importTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		Button exportTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
		exportTimeTable2.setText("Fahrplan Exportieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTimeTable2.setLayoutData(gridData);
		exportTimeTable2.addListener(SWT.Selection, new Listener() {
							
			public void handleEvent(Event arg0) {
								
							
								
			}
		});
				
		getGroupControlSmall().pack();
		getGroupControlSmall().setVisible(false);
		
	}

	public static Group getGroupControlSmall() {
		return groupControlSmall;
	}

	public void setGroupControlSmall(Group groupControlSmall) {
		this.groupControlSmall = groupControlSmall;
	}

	public static Group getGroupAddTimeTable() {
		return groupAddTimeTable;
	}

	public void setGroupAddTimeTable(Group groupAddTimeTable) {
		this.groupAddTimeTable = groupAddTimeTable;
	}

	public static Group getGroupEditTimeTable() {
		return groupEditTimeTable;
	}

	public void setGroupEditTimeTable(Group groupEditTimeTable) {
		this.groupEditTimeTable = groupEditTimeTable;
	}

	public static Group getGroupDeletTimeTable() {
		return groupDeletTimeTable;
	}

	public void setGroupDeletTimeTable(Group groupDeletTimeTable) {
		this.groupDeletTimeTable = groupDeletTimeTable;
	}

	public static Group getGroupImportTimeTable() {
		return groupImportTimeTable;
	}

	public void setGroupImportTimeTable(Group groupImportTimeTable) {
		this.groupImportTimeTable = groupImportTimeTable;
	}

	public static Group getGroupExportTimeTable() {
		return groupExportTimeTable;
	}

	public void setGroupExportTimeTable(Group groupExportTimeTable) {
		this.groupExportTimeTable = groupExportTimeTable;
	}

}
