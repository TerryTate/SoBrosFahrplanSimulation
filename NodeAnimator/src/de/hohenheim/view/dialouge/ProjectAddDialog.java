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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.ProjectEvents;
import de.hohenheim.controller.events.TimeTableEvents;
import de.hohenheim.controller.main.Main;

public class ProjectAddDialog extends Dialog {
	

	Shell parent;
	public static Shell dialog;
	public static Text idText;
	public static Text nameText;
	public static Combo comboChooseTrain;
	public static Combo comboChooseTimeTable;

    String message = "";

	public static Table linkTable; 

	public ProjectAddDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	 public void open() {
			
		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(300, 310);
	    
	  //Fenster  mittig setzen 
	    Toolkit myToolkit = Toolkit.getDefaultToolkit();
	    Dimension myDimension = myToolkit.getScreenSize();
	    dialog.setLocation((int) ((myDimension.getWidth() - dialog.getSize().x) / 2), 
	    		           (int) ((myDimension.getHeight() - dialog.getSize().y) / 2));
	    
	    
	    dialog.setText("Projekt hinzufügen");
	    dialog.setImage(new Image(null, "img/add.png"));
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	    
	    //Project ID
	    
	    Label id = new Label(dialog, SWT.NONE);
	    id.setText("ID : ");
	    
	    idText = new Text(dialog, SWT.NONE);
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    idText.setLayoutData(gridData);
	    
	    //Project Name
	    
	    Label name = new Label(dialog, SWT.NONE);
	    name.setText("Name : ");
	    
	    nameText = new Text(dialog, SWT.NONE);
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    nameText.setLayoutData(gridData);
	    nameText.setText("Unbennant");
	    
	    //Choose Train
	    
	    Label chooseTrain = new Label(dialog, SWT.NONE);
	    chooseTrain.setText("Zug wählen : ");
	    
	    comboChooseTrain = new Combo(dialog, SWT.READ_ONLY);
	    String[] trainsID = new String [Main.trainListAll.size()];
	    comboChooseTrain.setItems(loadTrainList(trainsID));
	    gridData = new GridData();
	    
	    gridData.horizontalAlignment = SWT.FILL;
	    comboChooseTrain.setLayoutData(gridData);
	    comboChooseTrain.select(0);
	    
        //Add Button 
	    
	    Button addButton = new Button(dialog, SWT.NONE);
		addButton.setText("Add");
		addButton.setImage(new Image(null,"img/add24.png"));
		addButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    ProjectEvents.addLink(true);   
				
			}
		});
	    
	    //Choose TimeTable (Fahrplan)	
	    
	    Label chooseTimeTable = new Label(dialog, SWT.NONE);
	    chooseTimeTable.setText("Fahrplan wählen : ");
	    
	    comboChooseTimeTable = new Combo(dialog, SWT.READ_ONLY);
	    String[] timetableID = new String [Main.timetableListAll.size()];
	    comboChooseTimeTable.setItems(loadTimetableList(timetableID));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.FILL;
	    comboChooseTimeTable.setLayoutData(gridData);
	    comboChooseTimeTable.select(0);
	    
	    // Remove Button
		
		Button removeButton = new Button(dialog, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.setImage(new Image(null,"img/Clear.png"));
		removeButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TimeTableEvents.removeMiddleStation(true);  
				
			}
		});
		    
	    
	    //Linked List
        Label room6 = new Label(dialog, SWT.NONE);
        room6.setText("");
	    
		Composite tableComposite = new Composite(dialog, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalSpan = 2; 
		gridData.horizontalAlignment = SWT.FILL;
		tableComposite.setLayoutData(gridData);
		tableComposite.setLayout(new FillLayout());
		
		linkTable = new Table(tableComposite, SWT.NONE);
		
		linkTable.setLinesVisible(true);

		TableColumn trains = new TableColumn(linkTable, SWT.None);
		TableColumn timetable = new TableColumn(linkTable, SWT.NONE);
		trains.setWidth(100);  
		timetable.setWidth(100);
		
		
        // Buttonn Composite
	    
	    Composite buttonComposite = new Composite(dialog, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(new Image(null, "img/Ok.png"));
		okButton.setImage(new Image(null, "img/add.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				if (projectCheckOk()){
			        ProjectEvents.addProject();
				}else{
					 MessageBox messageBox = new MessageBox(dialog, SWT.ERROR | SWT.OK);
			         messageBox.setMessage(message);    
			         messageBox.open();
				}
			}
		});
	    
	    // Cancel Button
		
		Button cancelButton = new Button(dialog, SWT.NONE);
		cancelButton.setText("Cancel");
		cancelButton.setImage(new Image(null, "img/Cancel.png"));
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

	protected boolean projectCheckOk() {
		
		message = "";	
		boolean check = true;
		
		try{
			if (linkTable.getItemCount() < 1){
				message = message + "Es muss mindestens ein Zug/Fahrplan paar \n" +
						"eingetragen sein !\n";
				check = false;
			}
			
			if (nameText.getText().equalsIgnoreCase("")){
				message = message + "Sie haben keinen Projekt Namen Eingegeben !\n";
				check = false;
			}
			
		    int id = Integer.parseInt(idText.getText());
		    if (id < 0){
		    	message = message + "Die Projekt ID muss eine Positive Zahl sein!";
		    	check = false;
		    }
		    for(int j = 0; j < Main.projectListAll.size(); j++){
			    if(id == Main.projectListAll.get(j).getId()){
			    
			        message = message + "Die eingegebene Projekt ID ist bereits vorhanden bitte  \n " +
			        		" geben sie eine andere 6 stellige Ziffer ein \n " +
			        		"und versuchen sie es erneut. "; 
			    	check = false;
			    }
		    }
		
		}catch(NumberFormatException e){
			message = message + "Die Zug ID darf nur aus Zahlen bestehen ! \n" +
	        		"und muss mindestens eine Ziffer haben!";
			
			check = false;
		}
		 
		return check;
	}

	private String[] loadTrainList(String[] trainsID) {
		
		for(int i=0; i < Main.trainListAll.size(); i++) {
			Integer id = Main.trainListAll.get(i).getID();
			trainsID[i] = id.toString();
		}
		
	
		return trainsID;
	}

	private String[] loadTimetableList(String[] timetableID) {
		
		for(int i=0; i < Main.timetableListAll.size(); i++) {
			Integer id = Main.timetableListAll.get(i).getId();
			timetableID[i] = id.toString();
		}
		
	
		return timetableID;
	}

}
