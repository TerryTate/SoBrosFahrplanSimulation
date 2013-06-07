package de.hohenheim.view.canvas;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.MenuBarEvents;
import de.hohenheim.controller.events.TimeTableEvents;


public class TimeTableControllerCanvas extends Canvas {
	
    
	private static Group groupControlSmall;
	
	public TimeTableControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
			
		// Group when the shellHeight < then 300 pixel
				
		groupControlSmall = (new Group(this, SWT.SHADOW_ETCHED_IN));
		getGroupControlSmall().setText("Zug Verwaltung");
		GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 1; 
        getGroupControlSmall().setLayout(gridLayout);
		        
		Button newTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
		newTimeTable2.setText("Fahrplan hinzufügen");		
		newTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
			    MenuBarEvents.addTimeTable();		
						
			}
		});
				
				 
		Button editTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
		editTimeTable2.setText("Fahrplan ändern");
		GridData gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
		gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 3; 
		editTimeTable2.setLayoutData(gridData);
		editTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
				 MenuBarEvents.edtitTimetable(false);	
						
			}
		});
				
		Button deleteTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
		deleteTimeTable2.setText("Fahrplan löschen");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTimeTable2.setLayoutData(gridData);
		deleteTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
				MenuBarEvents.deletTimetable();		
						
			}
		});
				
		Button importTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
		importTimeTable2.setText("Fahrplan Importieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importTimeTable2.setLayoutData(gridData);
		importTimeTable2.addListener(SWT.Selection, new Listener() {
					
			public void handleEvent(Event arg0) {
						
					
						
			}
		});
				
		Button exportTimeTable2 = new Button(getGroupControlSmall(), SWT.NONE);
		exportTimeTable2.setText("Fahrplan Exportieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTimeTable2.setLayoutData(gridData);
		exportTimeTable2.addListener(SWT.Selection, new Listener() {
							
			public void handleEvent(Event arg0) {
								
							
								
			}
		});
				
		getGroupControlSmall().pack();
		getGroupControlSmall().setVisible(true);
		
	}

	public static Group getGroupControlSmall() {
		return groupControlSmall;
	}

}
