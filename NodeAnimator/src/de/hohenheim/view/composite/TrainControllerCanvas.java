package de.hohenheim.view.composite;

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import de.hohenheim.modell.State;
import de.hohenheim.modell.Train;
import de.hohenheim.view.mobile.AnimationFigure;
import de.hohenheim.view.mobile.TrainFigure;

public class TrainControllerCanvas extends Canvas{

	public TrainControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
		
		Group group = new Group(this, SWT.SHADOW_ETCHED_IN);
	    group.setText("Zug Verwaltung");

		Button newTrain = new Button(group, SWT.NONE);
		newTrain.setBounds(5, 20, 150,25);
		newTrain.setText("Zug hinzufügen");
		newTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				
			}
		});
		
		
		
		group.pack();
		
	}

}
