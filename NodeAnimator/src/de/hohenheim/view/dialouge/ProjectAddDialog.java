package de.hohenheim.view.dialouge;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TrainEvents;

public class ProjectAddDialog extends Dialog {
	

	Shell parent;
	

	
	private Text idText;
	private Text nameText;
	private Combo comboChooseTrain;
	private Combo comboChooseTimeTable; 

	public ProjectAddDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	 public void open() {
			
		final Shell projectAdd = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    projectAdd.setSize(320, 310);
	    projectAdd.setLocation(500, 200);
	    projectAdd.setText("Projekt hinzufügen");
	    projectAdd.setImage(new Image(null, "img/add.png"));
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3; 
	    projectAdd.setLayout(gridLayout);
	    
	    //Project ID
	    
	    Label id = new Label(projectAdd, SWT.NONE);
	    id.setText("ID : ");
	    
	    idText = new Text(projectAdd, SWT.NONE);
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    idText.setLayoutData(gridData);
	    
	    //Project Name
	    
	    Label name = new Label(projectAdd, SWT.NONE);
	    name.setText("Name : ");
	    
	    nameText = new Text(projectAdd, SWT.NONE);
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    nameText.setLayoutData(gridData);
	    
	    //Choose Train
	    
	    Label chooseTrain = new Label(projectAdd, SWT.NONE);
	    chooseTrain.setText("Zug wählen : ");
	    
	    comboChooseTrain = new Combo(projectAdd, SWT.READ_ONLY);
	    //comboChooseTrain.setItems(typs);
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboChooseTrain.setLayoutData(gridData);
	    
	    //Choose TimeTable (Fahrplan)	
	    
	    Label chooseTimeTable = new Label(projectAdd, SWT.NONE);
	    chooseTimeTable.setText("Fahrplan wählen : ");
	    
	    comboChooseTimeTable = new Combo(projectAdd, SWT.READ_ONLY);
	    //comboChooseTimeTable.setItems(typs);
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboChooseTimeTable.setLayoutData(gridData);
	    
	    //Linked List
		    
// Buttonn Composite
	    
	    Composite buttonComposite = new Composite(projectAdd, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(projectAdd, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(new Image(null, "img/Ok.png"));
		okButton.setImage(new Image(null, "img/add.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TrainEvents.addNewTrain();
				
			}
		});
	    
	    // Cancel Button
		
		Button cancelButton = new Button(projectAdd, SWT.NONE);
		cancelButton.setText("Cancel");
		cancelButton.setImage(new Image(null, "img/Cancel.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    cancelButton.setLayoutData(gridData);
		
		cancelButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			   projectAdd.close();
				
			}
		});
	    
		projectAdd.open(); 
	}

}
