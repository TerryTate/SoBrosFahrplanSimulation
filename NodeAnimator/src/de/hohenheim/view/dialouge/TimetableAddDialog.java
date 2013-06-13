package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import de.hohenheim.controller.events.TimeTableEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.canvas.AnimationControllerCanvas;

public class TimetableAddDialog extends Dialog{
	
	Shell parent;
	String message = "";
	public static Shell dialog;
	public static Text idText;
	public static Text fahrplannameText;
	public static Combo comboStartstation;
	public static Combo comboEndstation;
	public static Combo comboMiddlestation; 
	public static Table midlestationTable;
	public static Spinner houre;
	public static Spinner minutes;
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
	    dialog.setSize(400, 410);
	    
	    //Fenster  mittig setzen 
	    Toolkit myToolkit = Toolkit.getDefaultToolkit();
	    Dimension myDimension = myToolkit.getScreenSize();
	    dialog.setLocation((int) ((myDimension.getWidth() - dialog.getSize().x) / 2), 
	    	           	   (int)((myDimension.getHeight() - dialog.getSize().y) / 2));
	    
	    
	    dialog.setText("Fahrplan hinzufügen");
	    dialog.setImage(new Image(null, "img/add.png"));
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	         
        // Set the Label and a Textfield for Timetable ID 
	    
	    Label id = new Label(dialog, SWT.NONE); 
	    id.setText("ID : ");
	    
	    idText = new Text(dialog, SWT.NONE); 
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    idText.setLayoutData(gridData);
	    idText.setTextLimit(6);
	    
	    // Set the Label and a Textfield for Timetable Name 
	    
	    Label timeTableName = new Label(dialog, SWT.NONE); 
	    timeTableName.setText("Fahrplanname : "); 
	    
	    fahrplannameText = new Text(dialog, SWT.NONE); 
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    fahrplannameText.setLayoutData(gridData);
	    fahrplannameText.setText("Unbenannt");
	    
	    //Set the Label and a spinner for the Starttime
	    Label starttime = new Label(dialog, SWT.NONE);
	    starttime.setText("Start Uhrzeit : ");
	    
	    Composite timeComposite = new Composite(dialog, SWT.NONE);
	    timeComposite.setLayout(new FillLayout());
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    timeComposite.setLayoutData(gridData);
	    
	    houre = new Spinner(timeComposite, SWT.NONE);
	    houre.setMaximum(23);
	   
	    
	    Label h = new Label(timeComposite, SWT.NONE);
	    h.setText("  h");
	    
	    minutes = new Spinner(timeComposite, SWT.NONE);
	    minutes.setMaximum(59);
	    
	    Label m = new Label(timeComposite, SWT.NONE);
	    m.setText("  m");
	    
	    
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
	    String[] items = AnimationControllerCanvas.getNodeNames();
	    comboStartstation.setItems(items);
	    comboStartstation.select(0);
	       
	    // Set Label and combo for Endstation
	    
	    Label endstation = new Label(dialog, SWT.NONE); 
	    endstation.setText("Endstation : "); 
	    
	    comboEndstation = new Combo(dialog, SWT.READ_ONLY);
	    comboEndstation.setItems(items);  
	    gridData.horizontalSpan = 2;
	    comboEndstation.setLayoutData(gridData);
	    comboEndstation.select(0);
	    
	    // Timetable Middlestation
	      
	    Label middlestation = new Label(dialog, SWT.NONE); 
	    middlestation.setText("Zwischenstationen : "); 
	    
	    comboMiddlestation = new Combo(dialog, SWT.READ_ONLY);
        comboMiddlestation.setItems(items);
        comboMiddlestation.select(0);
	  
	    Composite middlestationButtonC = new Composite(dialog, SWT.NONE);
	    middlestationButtonC.setLayout(new FillLayout());
	    
	    
        //Add Button 
	    
	    Button addButton = new Button(middlestationButtonC, SWT.NONE);
		addButton.setText("Add");
		addButton.setImage(new Image(null,"img/add24.png"));
		addButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				if(middleStationCheck()){
			        TimeTableEvents.addMiddleStation(true);   
				}else{
					MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
			        messageBox.setMessage(message);    
			        messageBox.open();
				}
			}
		});
	    
	    // Remove Button
		
		Button removeButton = new Button(middlestationButtonC, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.setImage(new Image(null,"img/Clear.png"));
		removeButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TimeTableEvents.removeMiddleStation(true);  
				
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
		okButton.setImage(new Image(null,"img/Ok.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				if(timetableCheck()){
				  
					TimeTableEvents.addTimeTable();
					
				}else{
					MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
			        messageBox.setMessage(message);    
			        messageBox.open();
				}
				
			}
		});
	    
	    // Cancel Button
		
		Button cancelButton = new Button(dialog, SWT.NONE);
		cancelButton.setText("Cancel");
		cancelButton.setImage(new Image(null,"img/Cancel.png"));
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

	protected boolean middleStationCheck() {
		message = "";
		if(midlestationTable.getItemCount() > 0){
		    if(comboMiddlestation.getText().equalsIgnoreCase(midlestationTable.getItem(midlestationTable.getItemCount()-1).getText())){
		    	message = message + "Es können keine zwei gleiche Stationen nacheinander hinzugefügt werden";
		    	return false;
		    }
		}else{
		    if(comboMiddlestation.getText().equalsIgnoreCase(comboStartstation.getText())){
                message = message + "Der erste Station in die Zwischenstationen muss \n" +
                		            "eine andere als die Startstation sein !";
                return false;		
		    }
		}
		return true;
	}

	protected boolean timetableCheck() {
		
		boolean idCheck = true;
		boolean name = true;
		boolean startend = true;
		boolean endmiddle = true;
		boolean drivingday = true;
		
		message = "";
		    
		    if((montag.getSelection() == false) && (dienstag.getSelection() == false) && (mittwoch.getSelection() == false) && (donerstag.getSelection() == false) && 
		       (freitag.getSelection() == false) && (samstag.getSelection() == false) && (sontag.getSelection() == false) && (alle.getSelection() == false)){
		    	message = message + "Es muss mindestens ein Fahrtag gewählt werden !\n";
		    	drivingday = false;
		    }
		
			if(fahrplannameText.getText().equalsIgnoreCase("")){
				message = message + "Es wurde kein Name für den Fahrplan angegeben !\n";
				name = false;
			}
			
			if(comboStartstation.getText().equalsIgnoreCase(comboEndstation.getText()) && (midlestationTable.getItemCount() == 0)){
				message = message + "Die Startstation und Endstation dürfen nur die \n" +
						            "selben sein wenn die Zwischenstationen nicht leer sind!\n";
				startend = false;
			}
			try{	
			    int id = Integer.parseInt(idText.getText());
			    for(int j = 0; j < Main.timetableListAll.size(); j++){
				    if(id == Main.timetableListAll.get(j).getId()){
				    	message = message + "Die eingegebene Fahrplan ID ist bereits vorhanden bitte \n" +
				        		"geben sie eine andere 1 - 6 stellige Ziffer ein \n" +
				        		"und versuchen sie es erneut.";
				    	idCheck = false;
				    	
				    }
			    }
			
	    	}catch(NumberFormatException e){
				
				message = message + "Die Zug ID darf nur aus Zahlen bestehen \n" +
		        		"und muss mindestens eine Ziffer haben! \n";
				idCheck = false;
				
			}	
			
		    try{	
		    	if(comboEndstation.getText().equalsIgnoreCase(midlestationTable.getItem(midlestationTable.getItemCount()-1).getText())){
		     		message = message + "Die letzte Zwischenstation darf nicht die \n" +
						            "selbe sein wie die Endstation !\n";
				
			    	endmiddle = false;
			    }
		}catch(IllegalArgumentException e){
			
		}
		
		
		if((idCheck == false) || (name == false) || (startend == false) || (endmiddle == false) || (drivingday == false)){
			return false;
		}else{
			return true;
		}
	}

}
