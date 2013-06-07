package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;

public class ProjectDeleteDialog extends Dialog {
	

	Shell parent;
	private Shell deleteProject;
	private Combo comboProject; 

	public ProjectDeleteDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
public void open(final boolean menu) {
		
		deleteProject = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    deleteProject.setSize(220, 310);
	    deleteProject.setText("Projekt löschen");
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    deleteProject.setLayout(gridLayout);
	    
	   
    	Label chooseTrain = new Label(deleteProject, SWT.NONE);
    	chooseTrain.setText("Wähle Fahrplan ID : ");
    	
    	comboProject = new Combo(deleteProject, SWT.READ_ONLY);
    	String[] trainsID = new String [Main.trainListAll.size()];
	    comboProject.setItems(loadProjectList(trainsID));
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboProject.setLayoutData(gridData);
	    
        // OK Button 
	    
	    Button okButton = new Button(deleteProject, SWT.NONE);
		okButton.setText("OK");
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TrainEvents.deleteTrain(menu);
				
			}
		});
	    
	    // Cancel Button
		
		Button cancelButton = new Button(deleteProject, SWT.NONE);
		cancelButton.setText("Cancel");
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    cancelButton.setLayoutData(gridData);
		
		cancelButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			   deleteProject.close();
				
			}
		});
			
		 
	    
		deleteProject.open();
	}

private String[] loadProjectList(String[] trainsID) {
	for(int i=0; i < Main.trainListAll.size(); i++) {
		Integer id = Main.trainListAll.get(i).getID();
		trainsID[i] = id.toString();
	}
	

	return trainsID;
}

}
