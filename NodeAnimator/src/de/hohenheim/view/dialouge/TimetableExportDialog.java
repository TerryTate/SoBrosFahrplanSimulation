package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.events.CentralEventController;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * The class TimetableExportDialog contains the GUI of exporting a timetable.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class TimetableExportDialog {

	Shell parent;
	public static Combo comboTimetables;
	public static Shell dialog;

	/**
	 * A Constructor for a new ProjectAdd dialog.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public TimetableExportDialog(Shell parent, int style) {
		super();
		parent = this.parent;
		
	}

	/**
	 * 
	 * The method open is responsible for the view elements of exporting a
	 * timetable.
	 * 
	 * @param menu
	 */
    public void open(final boolean menu) {
		
		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(215, 130);
	    
	    // Set the window in the middle
	    
	    Toolkit myToolkit = Toolkit.getDefaultToolkit();
	    Dimension myDimension = myToolkit.getScreenSize();
	    dialog.setLocation((int) ((myDimension.getWidth() - dialog.getSize().x) / 2), 
	    		           (int) ((myDimension.getHeight() - dialog.getSize().y) / 2));
	    
	    dialog.setText("Zug exportieren");
	    dialog.setImage(ImageHelper.export);
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	    
	   
    	Label chooseTimetable = new Label(dialog, SWT.NONE);
    	chooseTimetable.setText("W‰hle Fahrplan ID : ");
    	
    	comboTimetables = new Combo(dialog, SWT.READ_ONLY);
    	String[] timetablesID = new String [Main.getTimetableListAll().size()];
	    comboTimetables.setItems(loadTimetableList(timetablesID));
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboTimetables.setLayoutData(gridData);
	    comboTimetables.select(0);
	    
        // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(ImageHelper.ok);
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				CentralEventController.save(menu, 1);
				
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
		
	    
	    dialog.open();
	}

    /**
 	 * Load the timetableID's into a String[]
	 * 
	 * @param timetableID
	 * @return timetableID - the id's of the timetables
     */
	private String[] loadTimetableList(String[] timetablesID) {
	
		for(int i=0; i < Main.getTimetableListAll().size(); i++) {
			Integer id = Main.getTimetableListAll().get(i).getId();
			timetablesID[i] = id.toString();
		}
		
	
		return timetablesID;
	}
}
