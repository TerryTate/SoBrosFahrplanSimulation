package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
			
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(370, 310);
	    dialog.setText("Fahrplan hinzufügen");
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 4; 
	    dialog.setLayout(gridLayout);
	    
	    TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
	      
        // Train ID
	    
	    Label id = new Label(dialog, SWT.NONE); 
	    id.setText("ID : ");
	    
	    idText = new Text(dialog, SWT.NONE);
	     
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 3;
	
	    idText.setLayoutData(gridData);
	    
	    
	    // TimetableName
	    
	    Label timeTableName = new Label(dialog, SWT.NONE); 
	    timeTableName.setText("Fahrplanname : "); 
	    
	    fahrplannameText = new Text(dialog, SWT.NONE); 
	    gridData = new GridData();
	    gridData.horizontalSpan = 3;
	    fahrplannameText.setLayoutData(gridData);
	   
	    Label room6 = new Label(dialog, SWT.NONE);
	    
	    
	    // Timetable drivingDays 
	    
	    Label drivingDays = new Label(dialog, SWT.NONE); 
	    drivingDays.setText("Fahrtage : ");  
	    
	    new Button(dialog, SWT.CHECK).setText("Montag"); 
	    new Button(dialog, SWT.CHECK).setText("Dienstag");
	    new Button(dialog, SWT.CHECK).setText("Mittwoch");
	    Label room = new Label(dialog, SWT.NONE);
        new Button(dialog, SWT.CHECK).setText("Donerstag");
	    new Button(dialog, SWT.CHECK).setText("Freitag");
	    new Button(dialog, SWT.CHECK).setText("Samstag");
	    Label room3 = new Label(dialog, SWT.NONE);
	    new Button(dialog, SWT.CHECK).setText("Sontag");
	    new Button(dialog, SWT.CHECK).setText("Alle");
	    Label room2 = new Label(dialog, SWT.NONE);
	    
	    // Titmetable Startstation
	    
	    Label startstation = new Label(dialog, SWT.NONE); 
	    startstation.setText("Startstation : "); 
	    
        comboStartstation = new Combo(dialog, SWT.READ_ONLY);
        gridData.horizontalSpan = 3;
	   
	    comboStartstation.setLayoutData(gridData);
	  //  comboStartstation.setItems(speeds);
	       
	    // Timetable Endstation
	    
	    Label endstation = new Label(dialog, SWT.NONE); 
	    endstation.setText("Endstation : "); 
	    
	    comboEndstation = new Combo(dialog, SWT.READ_ONLY);
	  //  comboEndstation.setItems(priorities);  
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboEndstation.setLayoutData(gridData);
	    
	    // Timetable Middlestation
	    
	    Label middlestation = new Label(dialog, SWT.NONE); 
	    middlestation.setText("Zwischenstationen : "); 
	    
	    comboMiddlestation = new Combo(dialog, SWT.READ_ONLY);
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboMiddlestation.setLayoutData(gridData);
	   // comboMiddlestation.setItems("");
	  
        //Add Button 
	    
	    Button addButton = new Button(dialog, SWT.NONE);
		addButton.setText("ADD");
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    addButton.setLayoutData(gridData);
		
		addButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			   
				
			}
		});
	    
	    // Remove Button
		
		Button removeButton = new Button(dialog, SWT.NONE);
		removeButton.setText("Remove");
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    removeButton.setLayoutData(gridData);
		
		removeButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			  
				
			}
		});
	
	    // Buttonn Composite
	    
	    Composite buttonComposite = new Composite(dialog, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TimeTableEvents.addTimeTable();
				
			}
		});
	    
	    // Cancel Button
		
		Button cancelButton = new Button(dialog, SWT.NONE);
		cancelButton.setText("Cancel");
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    cancelButton.setLayoutData(gridData);
		
		cancelButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			   dialog.close();
				
			}
		});
	    
	    dialog.open();
	}

}
