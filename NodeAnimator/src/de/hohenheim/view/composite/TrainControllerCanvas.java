package de.hohenheim.view.composite;



import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TrainEvents;


public class TrainControllerCanvas extends Canvas{

	private static Text textID;
	private static Text textSpeed;
	private static Text textStartStation;
	private static Group groupAddTrain;
	private static Group groupControlSmall;
	private static Group groupEditTrain;

	public TrainControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
	
		// Group with all controllers for add a new Train
		
		setGroupAddTrain(new Group(this, SWT.SHADOW_ETCHED_IN));
	    getGroupAddTrain().setText("Zug hinzufügen");
        GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 3; 
	    getGroupAddTrain().setLayout(gridLayout);
        
	    
	    //Train ID
	    Label iD = new Label(getGroupAddTrain(), SWT.NONE); 
	    GridData gridData = new GridData();
	    gridData.horizontalAlignment = SWT.END;
	    iD.setText("ID : ");
	    iD.setLayoutData(gridData);
	    
	    setTextID(new Text(getGroupAddTrain(), SWT.BORDER)); 
	    getTextID().setText("645759"); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 2;
	    getTextID().setTextLimit(6);
	    getTextID().setLayoutData(gridData);
	    
	    //TrainSpeed
	    Label trainSpeed = new Label(getGroupAddTrain(), SWT.NONE); 
	    trainSpeed.setText("Geschwindigkeit : "); 
	    
	    setTextSpeed(new Text(getGroupAddTrain(), SWT.BORDER)); 
	    getTextSpeed().setText("300"); 
	    getTextID().setTextLimit(6);
	    getTextID().setLayoutData(gridData);
	    
	    Label kmH = new Label(getGroupAddTrain(), SWT.NONE); 
	    kmH.setText("km/h"); 
	    
	    //Startstation
	    
	    Label startstation = new Label(getGroupAddTrain(), SWT.NONE); 
	    startstation.setText("Anfangsstation : "); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.END;
	    startstation.setLayoutData(gridData);
	    
	    setTextStartStation(new Text(getGroupAddTrain(), SWT.BORDER)); 
	    getTextStartStation().setText("München"); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 2; 
	    getTextStartStation().setLayoutData(gridData);
	    
		Button newTrain = new Button(getGroupAddTrain(), SWT.NONE);
		newTrain.setText("Zug hinzufügen");
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    gridData.horizontalSpan = 3; 
	    newTrain.setLayoutData(gridData);
		
		newTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				TrainEvents.addNewTrain();
				
			}
		});
		
		getGroupAddTrain().pack();
		
		
	// Group with all controllers for edit a existing Train
		
		groupEditTrain = new Group(this, SWT.SHADOW_ETCHED_IN );
	    groupEditTrain.setText("Zug bearbeiten");
	    groupEditTrain.setBounds(0, 130, 0, 0);
        GridLayout gridLayout2 = new GridLayout(); 
        gridLayout.numColumns = 3; 
	    groupEditTrain.setLayout(gridLayout2);
        
	    
	   

	    
		Button editTrain = new Button(groupEditTrain, SWT.NONE);
		editTrain.setText("Zug ändern");
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    gridData.horizontalSpan = 3; 
	    editTrain.setLayoutData(gridData);
		
		editTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				

				
			}
		});
		
		groupEditTrain.pack();
		
		// Group when the shellHeight < then 300 pixel
		
		setGroupControlSmall(new Group(this, SWT.SHADOW_ETCHED_IN));
	    getGroupControlSmall().setText("Zug Verwaltung");
        GridLayout gridLayout3 = new GridLayout(); 
        gridLayout.numColumns = 1; 
	    getGroupControlSmall().setLayout(gridLayout3);


		Button newTrain2 = new Button(getGroupControlSmall(), SWT.NONE);
		newTrain2.setText("Zug hinzufügen");
	
		
		newTrain2.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		 
		Button editTrain2 = new Button(getGroupControlSmall(), SWT.NONE);
		editTrain2.setText("Zug ändern");
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    gridData.horizontalSpan = 3; 
	    editTrain2.setLayoutData(gridData);
		
		editTrain2.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
		
				
			}
		});
		
		
		
		getGroupControlSmall().pack();
		getGroupControlSmall().setVisible(false);
	}

	public static Text getTextID() {
		return textID;
	}

	public static void setTextID(Text textID) {
		TrainControllerCanvas.textID = textID;
	}

	public static Group getGroupControlSmall() {
		return groupControlSmall;
	}

	public static void setGroupControlSmall(Group groupControlSmall) {
		TrainControllerCanvas.groupControlSmall = groupControlSmall;
	}

	public static Group getGroupAddTrain() {
		return groupAddTrain;
	}

	public static void setGroupAddTrain(Group groupAddTrain) {
		TrainControllerCanvas.groupAddTrain = groupAddTrain;
	}

	public static Text getTextSpeed() {
		return textSpeed;
	}

	public static void setTextSpeed(Text textSpeed) {
		TrainControllerCanvas.textSpeed = textSpeed;
	}

	public static Text getTextStartStation() {
		return textStartStation;
	}

	public static void setTextStartStation(Text textStartStation) {
		TrainControllerCanvas.textStartStation = textStartStation;
	}

}
