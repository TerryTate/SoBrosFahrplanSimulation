package de.hohenheim.view.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;

import de.hohenheim.controller.events.TrainEvents;

public class TrainControllerCanvasSmall extends Canvas {
	
	public TrainControllerCanvasSmall(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
		
		Group groupAddTrain = new Group(this, SWT.SHADOW_ETCHED_IN);
	    groupAddTrain.setText("Zug Verwaltung");
        GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 3; 
	    groupAddTrain.setLayout(gridLayout);


		Button newTrain = new Button(groupAddTrain, SWT.NONE);
		newTrain.setText("Zug hinzufügen");
	
		
		newTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		
		
		groupAddTrain.pack();
	}    
}
