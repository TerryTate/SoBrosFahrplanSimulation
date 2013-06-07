package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import de.hohenheim.controller.main.Main;
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
	
	public static String [] Test = {"1", "2"};

	public TimetableEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
	
	 public void open(final boolean menu) {
			
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(320, 360);
	    dialog.setText("Fahrplan bearbeiten");
		
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
	    gridData.horizontalSpan = 3;
	    fahrplannameText.setLayoutData(gridData);
	  
	    
	    
	    // Timetable drivingDays 
	    
	    Label drivingDays = new Label(dialog, SWT.NONE); 
	    drivingDays.setText("Fahrtage : ");  
	    
	    new Button(dialog, SWT.CHECK).setText("Montag"); 
	    new Button(dialog, SWT.CHECK).setText("Dienstag");
	    Label room = new Label(dialog, SWT.NONE);
	    new Button(dialog, SWT.CHECK).setText("Mittwoch");
        new Button(dialog, SWT.CHECK).setText("Donerstag");
        Label room1 = new Label(dialog, SWT.NONE);
	    new Button(dialog, SWT.CHECK).setText("Freitag");
	    new Button(dialog, SWT.CHECK).setText("Samstag");
	    Label room3 = new Label(dialog, SWT.NONE);
	    new Button(dialog, SWT.CHECK).setText("Sontag");
	    new Button(dialog, SWT.CHECK).setText("Alle");
	   
	    
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

	protected void setText() {
		// TODO Auto-generated method stub
		
	}

	private String[] loadTimetableList(String[] timetableID) {
	

		for(int i=0; i < Main.timetableListAll.size(); i++) {
			Integer id = Main.timetableListAll.get(i).getId();
			timetableID[i] = id.toString();
		}
		
	
		return timetableID;
		
	}
}
