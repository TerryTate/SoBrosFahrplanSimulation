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

public class ProjectExportDialog {

	
	private Shell parent;
	public static Shell dialog;
	public static Combo comboProjects;


	public ProjectExportDialog(Shell parent, int style) {
		super();
		parent = this.parent;
		
	}

    public void open(final boolean menu) {
		
		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(215, 130);
	    
	  //Fenster  mittig setzen 
	    Toolkit myToolkit = Toolkit.getDefaultToolkit();
	    Dimension myDimension = myToolkit.getScreenSize();
	    dialog.setLocation((int) ((myDimension.getWidth() - dialog.getSize().x) / 2), 
	    		           (int) ((myDimension.getHeight() - dialog.getSize().y) / 2));
	    
	    dialog.setText("Zug exportieren");
	    dialog.setImage(ImageHelper.export);
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	    
	   
    	Label chooseProject = new Label(dialog, SWT.NONE);
    	chooseProject.setText("Wähle Projekt ID : ");
    	
    	comboProjects = new Combo(dialog, SWT.READ_ONLY);
    	String[] projectID = new String [Main.projectListAll.size()];
	    comboProjects.setItems(loadProjectList(projectID));
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboProjects.setLayoutData(gridData);
	    comboProjects.select(0);
	    
        // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(ImageHelper.ok);
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				CentralEventController.save(menu, 2);
				
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


	private String[] loadProjectList(String[] projectID) {
	
		for(int i=0; i < Main.projectListAll.size(); i++) {
			Integer id = Main.projectListAll.get(i).getId();
			projectID[i] = id.toString();
		}
		
	
		return projectID;
	}
}
