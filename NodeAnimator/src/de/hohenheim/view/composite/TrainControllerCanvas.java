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

	public static Text textID;
	public static Text textSpeed;
	public static Text textStartStation;
	public static Group groupAddTrain;
	public static Group groupControlSmall;

	public TrainControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
		
		groupAddTrain = new Group(this, SWT.SHADOW_ETCHED_IN);
	    groupAddTrain.setText("Zug Verwaltung");
        GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 3; 
	    groupAddTrain.setLayout(gridLayout);
        
	    
	    //Train ID
	    Label label = new Label(groupAddTrain, SWT.NONE); 
	    GridData gridData = new GridData();
	    gridData.horizontalAlignment = SWT.END;
	    label.setText("ID : ");
	    label.setLayoutData(gridData);
	    
	    textID = new Text(groupAddTrain, SWT.BORDER); 
	    textID.setText("645759"); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 2;
	    textID.setTextLimit(6);
	    textID.setLayoutData(gridData);
	    
	    //TrainSpeed
	    Label trainSpeed = new Label(groupAddTrain, SWT.NONE); 
	    trainSpeed.setText("Geschwindigkeit : "); 
	    
	    textSpeed = new Text(groupAddTrain, SWT.BORDER); 
	    textSpeed.setText("300"); 
	    textID.setTextLimit(6);
	    textID.setLayoutData(gridData);
	    
	    Label kmH = new Label(groupAddTrain, SWT.NONE); 
	    kmH.setText("km/h"); 
	    
	    //Startstation
	    Label startstation = new Label(groupAddTrain, SWT.NONE); 
	    startstation.setText("Anfangsstation : "); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.END;
	    startstation.setLayoutData(gridData);
	    
	    textStartStation = new Text(groupAddTrain, SWT.BORDER); 
	    textStartStation.setText("München"); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 2; 
	    textStartStation.setLayoutData(gridData);
	    
		Button newTrain = new Button(groupAddTrain, SWT.NONE);
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
		
		
		
		groupAddTrain.pack();
		

		groupControlSmall = new Group(this, SWT.SHADOW_ETCHED_IN);
	    groupControlSmall.setText("Zug Verwaltung");
        GridLayout gridLayout2 = new GridLayout(); 
        gridLayout.numColumns = 1; 
	    groupControlSmall.setLayout(gridLayout2);


		Button newTrain2 = new Button(groupControlSmall, SWT.NONE);
		newTrain2.setText("Zug hinzufügen");
	
		
		newTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		
		
		groupControlSmall.pack();
		groupControlSmall.setVisible(false);
	}

}
