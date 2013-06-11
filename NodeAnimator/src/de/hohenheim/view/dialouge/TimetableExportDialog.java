package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.events.MenuBarEvents;
import de.hohenheim.controller.main.Main;

public class TimetableExportDialog {

	Shell parent;
	public static Combo comboTimetables;
	public static Shell dialog;

	public TimetableExportDialog(Shell parent, int style) {
		super();
		parent = this.parent;
		
	}

    public void open(final boolean menu) {
		
		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(215, 130);
	    dialog.setText("Zug exportieren");
	    dialog.setImage(new Image(null,"img/Delete.png"));
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	    
	   
    	Label chooseTimetable = new Label(dialog, SWT.NONE);
    	chooseTimetable.setText("Wähle Fahrplan ID : ");
    	
    	comboTimetables = new Combo(dialog, SWT.READ_ONLY);
    	String[] timetablesID = new String [Main.timetableListAll.size()];
	    comboTimetables.setItems(loadTimetableList(timetablesID));
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboTimetables.setLayoutData(gridData);
	    comboTimetables.select(0);
	    
        // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(new Image(null,"img/Ok.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				MenuBarEvents.saveTimetable(menu);
				
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


	private String[] loadTimetableList(String[] timetablesID) {
	
		for(int i=0; i < Main.timetableListAll.size(); i++) {
			Integer id = Main.timetableListAll.get(i).getId();
			timetablesID[i] = id.toString();
		}
		
	
		return timetablesID;
	}
}
