package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TimeTableEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.mobile.ImageHelper;

public class TimetableEditDialog extends Dialog{
	
	Shell parent;
	public static Shell dialog;
	public static Text idText;
	public static Text fahrplannameText;
	public static Combo comboStartstation;
	public static Combo comboEndstation;
	public static Combo comboMiddlestation;
	public static Table midlestationTable;
	public static Combo comboTimetable;
	public static Spinner houre;
	public static Spinner minutes;
	public static Button montag;
	public static Button dienstag;
	public static Button mittwoch;
	public static Button donerstag;
	public static Button freitag;
	public static Button samstag;
	public static Button sontag;
	public static Button alle;
	String message = "";

	public TimetableEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
	
	 public void open(final boolean menu) {
			
		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(400, 440);
	    
	  //Fenster  mittig setzen 
	    Toolkit myToolkit = Toolkit.getDefaultToolkit();
	    Dimension myDimension = myToolkit.getScreenSize();
	    dialog.setLocation((int) ((myDimension.getWidth() - dialog.getSize().x) / 2), 
	    			       (int) ((myDimension.getHeight() - dialog.getSize().y) / 2));
	    
	    
	    dialog.setText("Fahrplan bearbeiten");
	    dialog.setImage(ImageHelper.edit);
		
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	    
	    TableItem [] rowData = CompositeTimeTable.getTimeTableTable().getSelection();
	    
	    if (menu == true){
	    	
	    	Label chooseTimetable = new Label(dialog, SWT.NONE);
	    	chooseTimetable.setText("Wähle Fahrplan ID : ");
	    	
	    	comboTimetable = new Combo(dialog, SWT.READ_ONLY);
	    	String[] timetableID = new String [Main.timetableListAll.size()];
		    comboTimetable.setItems(loadTimetableList(timetableID));
		    comboTimetable.select(0);
		    

		    comboTimetable.addSelectionListener(new SelectionListener() {
		       
		    	public void widgetSelected(SelectionEvent e) {
		          
		    	    setText();
		    		
		        }

		        public void widgetDefaultSelected(SelectionEvent e) {
		         
		        }
		      });
		    
		    GridData gridData = new GridData();
		    gridData.horizontalSpan = 2;
		    gridData.horizontalAlignment = SWT.FILL;
		    comboTimetable.setLayoutData(gridData);
		    
	    	
		}
	      
        // Train ID
	    
	    Label id = new Label(dialog, SWT.NONE); 
	    id.setText("ID : ");
	    
	    idText = new Text(dialog, SWT.NONE);
	     
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	
	    idText.setLayoutData(gridData);
	    
	    
	    // TimetableName
	    
	    Label timeTableName = new Label(dialog, SWT.NONE); 
	    timeTableName.setText("Fahrplanname : "); 
	    
	    fahrplannameText = new Text(dialog, SWT.NONE); 
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    fahrplannameText.setLayoutData(gridData);
	    
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
	    
	    
	    // Timetable drivingDays 
	    
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
	   
	    
	    // Titmetable Startstation
	    
	    Label startstation = new Label(dialog, SWT.NONE); 
	    startstation.setText("Startstation : "); 
	    
        comboStartstation = new Combo(dialog, SWT.READ_ONLY);
        gridData.horizontalSpan = 2;
	   
	    comboStartstation.setLayoutData(gridData);
	    
	    String[] items = AnimationControllerCanvas.getNodeNames();
	    comboStartstation.setItems(items);
	  
	       
	    // Timetable Endstation
	    
	    Label endstation = new Label(dialog, SWT.NONE); 
	    endstation.setText("Endstation : "); 
	    
	    comboEndstation = new Combo(dialog, SWT.READ_ONLY);
	    comboEndstation.setItems(items);  
	    gridData.horizontalSpan = 2;
	    comboEndstation.setLayoutData(gridData);
	    
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
		addButton.setImage(ImageHelper.add);;
		addButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {

                if(middleStationCheck()){
			        TimeTableEvents.addMiddleStation(false);   
				}else{
					MessageBox messageBox = new MessageBox(dialog, SWT.ERROR | SWT.OK);
			        messageBox.setMessage(message);    
			        messageBox.open();
				}
			
			}
		});
	    
	    // Remove Button
		
		Button removeButton = new Button(middlestationButtonC, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.setImage(ImageHelper.remove);
		removeButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TimeTableEvents.removeMiddleStation(false);  
				
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
		
		 if(menu == false){
		    	
			 for(int i = 0; i < Main.timetableListAll.size(); i++ ){
					
					Timetable tt = Main.timetableListAll.get(i);
					
					if (Integer.parseInt(rowData[0].getText()) == tt.getId()){
						
						Integer idTimetable= Integer.valueOf(tt.getId());
						Integer startstationTimetable = Integer.valueOf(tt.getStartstation());
						Integer endstationTimetable = Integer.valueOf(tt.getEndstation());
			
						idText.setText(idTimetable.toString());
						fahrplannameText.setText(tt.getName());
						houre.setSelection(tt.getStartHouer());
						minutes.setSelection(tt.getStartMinutes());
						for(int j = 0; j < tt.getDrivingdays().size(); j++){
							
						    if( tt.getDrivingdays().get(j).equalsIgnoreCase("Mo")){
						    	
						    	montag.setSelection(true);
						    	
						    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Di")){
						    	
						    	dienstag.setSelection(true);
						    	
						    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Mi")){
						    	
						    	mittwoch.setSelection(true);
						    	
						    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Do")){
						    	
						    	donerstag.setSelection(true);
						    	
						    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Fr")){
						    	
						    	freitag.setSelection(true);
						    	
						    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Sa")){
						    	
						    	samstag.setSelection(true);
						    	
						    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("So")){
						    	
						    	sontag.setSelection(true);
						    	
						    }
						}
						comboStartstation.setText(startstationTimetable.toString());
						comboEndstation.setText(endstationTimetable.toString());
					
						Integer middle; 
					
						for(int j = 0; j < tt.getMiddlestations().size(); j++){
							
							middle =  Integer.valueOf(tt.getMiddlestations().get(j));
							 
							TableItem item = new TableItem(TimetableEditDialog.midlestationTable, SWT.NONE);
							item.setText(middle.toString());
						}
			        		
					}
				}
		        
		}   
		
	    // Buttonn Composite
	    
	    Composite buttonComposite = new Composite(dialog, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(ImageHelper.ok);
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				if (checkOk()){
			        TimeTableEvents.editTimeTable(menu);
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
		cancelButton.setImage(ImageHelper.cancel);
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    cancelButton.setLayoutData(gridData);
		
		cancelButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			   dialog.close();
				
			}
		});
		if (menu == true){
			setText();
		}
		    
		dialog.open();
	}

	protected boolean middleStationCheck() {
		message = "";
		if(midlestationTable.getItemCount() > 0){
		    if(comboMiddlestation.getText().equalsIgnoreCase(midlestationTable.getItem(midlestationTable.getItemCount()-1).getText())){
		    	message = message + "Es sind zwei identische Zwischenstationen vorhanden. " +
		    						"Dies ist leider nicht möglich!\n"+"\r\n";
		    	return false;
		    }
		}else{
		    if(comboMiddlestation.getText().equalsIgnoreCase(comboStartstation.getText())){
                message = message + "Die Zwischenstation ist identisch mit der Startstation. \n" +
                					"Bitte geben Sie eine andere, nicht identische Zwischenstation ein!\n"+"\r\n";
                return false;		
		    }
		}
		return true;
		
	}

	protected boolean checkOk() {
		
		boolean check = true;
		
		message = "";
		    
		    if((montag.getSelection() == false) && (dienstag.getSelection() == false) && (mittwoch.getSelection() == false) && (donerstag.getSelection() == false) && 
		       (freitag.getSelection() == false) && (samstag.getSelection() == false) && (sontag.getSelection() == false) && (alle.getSelection() == false)){
		    	message = message + "Bitte wählen Sie mindestens einen Fahrtag aus!\n"+"\r\n";
		    	check = false;
		    }
		
			if(fahrplannameText.getText().equalsIgnoreCase("")){
				message = message + "Bitte geben Sie einen Fahrplan-Name ein!\n"+"\r\n";
				check = false;
			}
			
			if(comboStartstation.getText().equalsIgnoreCase(comboEndstation.getText()) && (midlestationTable.getItemCount() == 0)){
				message = message + "Die Startstation und Endstation dürfen nur die \n" +
						            "selben sein wenn die Zwischenstationen nicht leer sind!\n";
				check = false;
			}
			
            for(int j = 0; j < (midlestationTable.getItemCount()-1); j++){
				
				if(midlestationTable.getItem(j).getText().equalsIgnoreCase(midlestationTable.getItem(j + 1).getText())){
				    message = message + "Es sind zwei identische Zwischenstationen vorhanden. \n" +
	    								"Bitte löschen Sie einen dieser beiden Stationen!\n"+"\r\n";
				    check = false;
				}
				
			}
		try{	
			if(comboEndstation.getText().equalsIgnoreCase(midlestationTable.getItem(midlestationTable.getItemCount()-1).getText())){
				message = message + "Die Zwischenstation darf nicht identisch mit der \n" +
								    "Endstation sein!\n"+"\r\n";
				
				check = false;
			}
		}catch(IllegalArgumentException e){
			
		}
		try{	
		    int id = Integer.parseInt(idText.getText());
		   
		    if(id < 0){
		    	message = message + "Die Fahrplan-ID muss eine positive Zahl sein!\n"+"\r\n";
		    	check = false;
		    }
		    
    	}catch(NumberFormatException e){
			
			message = message + "Die Zug ID darf nur aus Zahlen bestehen \n" +
	        		"und muss mindestens eine Ziffer haben! \n";
			check = false;
			
		}
		
		
	    return check;
		
	}

	protected void setText() {
        
		for(int i = 0; i < Main.timetableListAll.size(); i++ ){
			
			Timetable tt = Main.timetableListAll.get(i);
			
			if (Integer.valueOf(comboTimetable.getText()) == tt.getId()){
				
				Integer id = Integer.valueOf(tt.getId());
				Integer startstation = Integer.valueOf(tt.getStartstation());
				Integer endstation = Integer.valueOf(tt.getEndstation());
				
				idText.setText(id.toString());
				fahrplannameText.setText(tt.getName());
				houre.setSelection(tt.getStartHouer());
				minutes.setSelection(tt.getStartMinutes());
				for(int j = 0; j < tt.getDrivingdays().size(); j++){
					
				    if( tt.getDrivingdays().get(j).equalsIgnoreCase("Mo")){
				    	
				    	montag.setSelection(true);
				    	
				    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Di")){
				    	
				    	dienstag.setSelection(true);
				    	
				    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Mi")){
				    	
				    	mittwoch.setSelection(true);
				    	
				    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Do")){
				    	
				    	donerstag.setSelection(true);
				    	
				    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Fr")){
				    	
				    	freitag.setSelection(true);
				    	
				    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("Sa")){
				    	
				    	samstag.setSelection(true);
				    	
				    }else if(tt.getDrivingdays().get(j).equalsIgnoreCase("So")){
				    	
				    	sontag.setSelection(true);
				    	
				    }
				}
				comboStartstation.setText(startstation.toString());
				comboEndstation.setText(endstation.toString());
			
				Integer middlestation; 
			
				for(int j = 0; j < tt.getMiddlestations().size(); j++){
					
					middlestation =  Integer.valueOf(tt.getMiddlestations().get(j));
					 
					TableItem item = new TableItem(TimetableEditDialog.midlestationTable, SWT.NONE);
					item.setText(middlestation.toString());
				}
	        		
			}
		    
		}
  		
	}

	private String[] loadTimetableList(String[] timetableID) {
	

		for(int i=0; i < Main.timetableListAll.size(); i++) {
			Integer id = Main.timetableListAll.get(i).getId();
			timetableID[i] = id.toString();
		}
		
	
		return timetableID;
		
	}
}
