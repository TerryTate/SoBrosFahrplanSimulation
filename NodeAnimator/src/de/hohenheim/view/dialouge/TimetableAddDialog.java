package de.hohenheim.view.dialouge;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TimeTableEvents;
import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeTrain;

public class TimetableAddDialog extends Dialog{
	
	Shell parent;
	public static Shell dialog;
	public static Text idText;
	public static Text fahrplannameText;
	public static Combo comboStartstation;
	public static Combo comboEndstation;
	public static Combo comboMiddlestation; 
	public static Table midlestationTable;
	
	public static String [] Test = {"1", "2"};
	final int TEXT_MARGIN = 3;
	
	public static Button dienstag;
	public static Button mittwoch;
	public static Button donerstag;
	public static Button freitag;
	public static Button samstag;
	public static Button sontag;
	public static Button alle;
	public static Button montag;
	
	
	public TimetableAddDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	 public void open() {
		
		// Set a new dialog with a GridLayout wit 3 columns 
		 
		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(370, 340);
	    dialog.setText("Fahrplan hinzufügen");
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	         
        // Set the Label and a Textfield for Timetable ID 
	    
	    Label id = new Label(dialog, SWT.NONE); 
	    id.setText("ID : ");
	    
	    idText = new Text(dialog, SWT.NONE); 
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    idText.setLayoutData(gridData);
	    
	    // Set the Label and a Textfield for Timetable Name 
	    
	    Label timeTableName = new Label(dialog, SWT.NONE); 
	    timeTableName.setText("Fahrplanname : "); 
	    
	    fahrplannameText = new Text(dialog, SWT.NONE); 
	    gridData = new GridData();
	    gridData.horizontalSpan = 3;
	    fahrplannameText.setLayoutData(gridData);
	  
	    // Timetable drivingDays --> Label drivingdays and Checkboxes
	    
	    Label drivingDays = new Label(dialog, SWT.NONE); 
	    drivingDays.setText("Fahrtage : ");  
	    
	    montag = new Button(dialog, SWT.CHECK);
	    montag.setText("Montag");
	    dienstag = new Button(dialog, SWT.CHECK);
	    dienstag.setText("Dienstag");
	    
	    Label room = new Label(dialog, SWT.NONE);
	    
	    mittwoch = new Button(dialog, SWT.CHECK);
	    mittwoch.setText("Mittwoch");
        donerstag = new Button(dialog, SWT.CHECK);
        donerstag.setText("Donerstag");
        
        Label room1 = new Label(dialog, SWT.NONE);
        
	    freitag = new Button(dialog, SWT.CHECK);
	    freitag.setText("Freitag");
	    samstag = new Button(dialog, SWT.CHECK);
	    samstag.setText("Samstag");
	    
	    Label room3 = new Label(dialog, SWT.NONE);
	    
	    sontag = new Button(dialog, SWT.CHECK);
	    sontag.setText("Sontag");
	    alle = new Button(dialog, SWT.CHECK);
	    alle.setText("Alle");
	   
	    // Set Label and combo for Startstation
	    
	    Label startstation = new Label(dialog, SWT.NONE); 
	    startstation.setText("Startstation : "); 
	    
        comboStartstation = new Combo(dialog, SWT.READ_ONLY);
        gridData.horizontalSpan = 2;
	   
	    comboStartstation.setLayoutData(gridData);
	    comboStartstation.setItems(Test);
	       
	    // Set Label and combo for Endstation
	    
	    Label endstation = new Label(dialog, SWT.NONE); 
	    endstation.setText("Endstation : "); 
	    
	    comboEndstation = new Combo(dialog, SWT.READ_ONLY);
	    comboEndstation.setItems(Test);  
	    gridData.horizontalSpan = 2;
	    comboEndstation.setLayoutData(gridData);
	    
	    // Timetable Middlestation
	      
	    Label middlestation = new Label(dialog, SWT.NONE); 
	    middlestation.setText("Zwischenstationen : "); 
	    
	    comboMiddlestation = new Combo(dialog, SWT.READ_ONLY);
        comboMiddlestation.setItems(Test);
	  
	    
	    Composite middlestationButtonC = new Composite(dialog, SWT.NONE);
	    middlestationButtonC.setLayout(new FillLayout());
	    
	    
        //Add Button 
	    
	    Button addButton = new Button(middlestationButtonC, SWT.NONE);
		addButton.setText("ADD");
		addButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TimeTableEvents.addMiddleStation();   
				
			}
		});
	    
	    // Remove Button
		
		Button removeButton = new Button(middlestationButtonC, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TimeTableEvents.removeMiddleStation();  
				
			}
		});
		
		Label room6 = new Label(dialog, SWT.NONE);
	    
		Composite tableComposite = new Composite(dialog, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalSpan = 2; 
		gridData.horizontalAlignment = SWT.FILL;
		tableComposite.setLayoutData(gridData);
		tableComposite.setLayout(new FillLayout());
		
		midlestationTable = new Table(tableComposite, SWT.NONE);
		
		midlestationTable.setLinesVisible(true);

		TableColumn midleStations = new TableColumn(midlestationTable, SWT.None);
		
		midleStations.setWidth(180);
		
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
