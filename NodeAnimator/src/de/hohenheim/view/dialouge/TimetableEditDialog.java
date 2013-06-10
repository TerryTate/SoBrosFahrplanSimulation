package de.hohenheim.view.dialouge;

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
import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.composite.CompositeTrain;

public class TimetableEditDialog extends Dialog{
	
	Shell parent;
	private Text idText;
	private Text fahrplannameText;
	private Combo comboStartstation;
	private Combo comboEndstation;
	private Combo comboMiddlestation;
	private Table midlestationTable;
	private Combo comboTimetable;
	private Spinner houre;
	private Spinner minutes;
	private Button montag;
	private Button dienstag;
	private Button mittwoch;
	private Button donerstag;
	private Button freitag;
	private Button samstag;
	private Button sontag;
	private Button alle;
	
	public static String [] Test = {"1", "2"};

	public TimetableEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
	
	 public void open(final boolean menu) {
			
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(400, 440);
	    dialog.setText("Fahrplan bearbeiten");
	    dialog.setImage(new Image(null, "img/Edit2.png"));
		
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
	    comboStartstation.setItems(Test);
	       
	    // Timetable Endstation
	    
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
		removeButton.setImage(new Image(null,"img/Clear.png"));
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
		
		 if(menu == false){
		    	
	        idText.setText(rowData[0].getText(0));
	       	fahrplannameText.setText(rowData[0].getText(1));
	       	comboStartstation.setText(rowData[0].getText(3));
	       	comboEndstation.setText(rowData[0].getText(4));
	      //  midlestationTable.setText(rowData[0].getText(4));
		        
		}   
		
	    // Buttonn Composite
	    
	    Composite buttonComposite = new Composite(dialog, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(new Image(null,"img/OK.png"));
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

	protected void setText() {
        
		for(int i = 0; i < Main.timetableListAll.size(); i++ ){
			
			Timetable tt = Main.timetableListAll.get(i);
			
			if (Integer.valueOf(comboTimetable.getText()) == tt.getId()){
				
				Integer id = Integer.valueOf(tt.getId());
	
				idText.setText(id.toString());
				fahrplannameText.setText(tt.getName());
				
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
