package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TimeTableEvents;
import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeTrain;

public class TimetableAddDialog extends Dialog{
	
	Shell parent;
	private Text idText;
	private Text fahrplannameText;
	private Combo comboStartstation;
	private Combo comboEndstation;
	private Combo comboMiddlestation; 

	public TimetableAddDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	 public void open() {
			
		final Shell addTimeTable = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    addTimeTable.setSize(370, 310);
	    addTimeTable.setLocation(500, 200);
	    addTimeTable.setText("Fahrplan hinzufügen");
	    addTimeTable.setImage(new Image(null, "img/add.png"));
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 4; 
	    addTimeTable.setLayout(gridLayout);
	    
	    TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
	      
        // Train ID
	    
	    Label id = new Label(addTimeTable, SWT.NONE); 
	    id.setText("ID :   ");
	    
	    idText = new Text(addTimeTable, SWT.NONE);
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 3;
	    idText.setLayoutData(gridData);
	    
	    
	    // TimetableName
	    
	    Label timeTableName = new Label(addTimeTable, SWT.NONE); 
	    timeTableName.setText("Fahrplanname : "); 
	    
	    fahrplannameText = new Text(addTimeTable, SWT.NONE); 
	    gridData = new GridData();
	    gridData.horizontalSpan = 3;
	    fahrplannameText.setLayoutData(gridData);
	   
	  
	    
	    // Timetable drivingDays 
	    
	    Label drivingDays = new Label(addTimeTable, SWT.NONE); 
	    drivingDays.setText("Fahrtage : ");  
	    
	    new Button(addTimeTable, SWT.CHECK).setText("Montag"); 
	    new Button(addTimeTable, SWT.CHECK).setText("Dienstag");
	    new Button(addTimeTable, SWT.CHECK).setText("Mittwoch");
	    Label room = new Label(addTimeTable, SWT.NONE);
        new Button(addTimeTable, SWT.CHECK).setText("Donerstag");
	    new Button(addTimeTable, SWT.CHECK).setText("Freitag");
	    new Button(addTimeTable, SWT.CHECK).setText("Samstag");
	    Label room3 = new Label(addTimeTable, SWT.NONE);
	    new Button(addTimeTable, SWT.CHECK).setText("Sontag");
	    new Button(addTimeTable, SWT.CHECK).setText("Alle");
	    Label room2 = new Label(addTimeTable, SWT.NONE);
	    
	    // Titmetable Startstation
	    
	    Label startstation = new Label(addTimeTable, SWT.NONE); 
	    startstation.setText("Startstation : "); 
	    
        comboStartstation = new Combo(addTimeTable, SWT.READ_ONLY);
        gridData.horizontalSpan = 3;
	   
	    comboStartstation.setLayoutData(gridData);
	  //  comboStartstation.setItems(speeds);
	       

	    
	    // Timetable Endstation
	    
	    Label endstation = new Label(addTimeTable, SWT.NONE); 
	    endstation.setText("Endstation : "); 
	    
	    comboEndstation = new Combo(addTimeTable, SWT.READ_ONLY);
	  //  comboEndstation.setItems(priorities);  
	    gridData.horizontalSpan = 3;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboEndstation.setLayoutData(gridData);

	    
	    // Timetable Middlestation
	    
	    Label middlestation = new Label(addTimeTable, SWT.NONE); 
	    middlestation.setText("Zwischenstationen : "); 
	    
	    comboMiddlestation = new Combo(addTimeTable, SWT.READ_ONLY);
	    gridData.horizontalSpan = 3;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboMiddlestation.setLayoutData(gridData);
	   // comboMiddlestation.setItems("");
	  
	  
	    
        //Add Button 
	    
	    Button addButton = new Button(addTimeTable, SWT.NONE);
		addButton.setText("Add");
		addButton.setImage(new Image(null,"img/add24.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    addButton.setLayoutData(gridData);
		
		addButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				TimeTableEvents.addTimeTable();
				
			}
		});
	    
	    // Remove Button
		
		Button removeButton = new Button(addTimeTable, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.setImage(new Image(null,"img/Clear.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    removeButton.setLayoutData(gridData);
		
		removeButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				TimeTableEvents.editTimeTable();
				
			}
		});
	
	    // Button Composite
	    
	    Composite buttonComposite = new Composite(addTimeTable, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(addTimeTable, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(new Image(null,"img/Ok.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TimeTableEvents.addTimeTable();
				
			}
		});
	    
	    // Cancel Button
		
		Button cancelButton = new Button(addTimeTable, SWT.NONE);
		cancelButton.setText("Cancel");
		cancelButton.setImage(new Image(null,"img/Cancel.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    cancelButton.setLayoutData(gridData);
		
		cancelButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			   addTimeTable.close();
				
			}
		});
	    
	    addTimeTable.open();
	}

}
