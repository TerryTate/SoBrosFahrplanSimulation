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

public class TrainAddDialog extends Dialog{
	
	
	Shell parent;
	String[] typs = {"S-Bahn", "ICE", "IC", "RegioBahn", "Güterzug", "Dampflock"};
	String[] speeds = {"80", "120", "150", "200", "300"};
	String[] priorities = {"Sehr wichtig", "Wichtig", "Normal", "Irrelevant"};
	String[] ladung = {"keine", "Rohstoffe", "Müll", "Fertige Produkte", "Brennstoffe"};
	
	public static Shell dialog;
	public static Combo comboTypOfTrain;
	public static Combo comboLadungen;
	public static Combo comboPriority;
	public static Combo comboSpeed;
	public static Text idText;

	public TrainAddDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
		
	}

	public void open() {
		
		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(300, 240);
	    dialog.setText("Zug erstellen");
	    dialog.setImage(new Image(null,"img/add.png"));
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	    
	    // Train ID
	    
	    Label id = new Label(dialog, SWT.NONE); 
	    id.setText("ID : ");
	    
	    idText = new Text(dialog, SWT.NONE);
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    idText.setLayoutData(gridData);
	    
	    // Train Typ
	    
	    Label typOfTrain = new Label(dialog, SWT.NONE); 
	    typOfTrain.setText("ZugTyp : "); 
	    
	    comboTypOfTrain = new Combo(dialog, SWT.READ_ONLY);
	    comboTypOfTrain.setItems(typs);
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboTypOfTrain.setLayoutData(gridData);

	    //Train Speed
	    
	    Label trainSpeed = new Label(dialog, SWT.NONE); 
	    trainSpeed.setText("Geschwindigkeit : "); 
	    
        comboSpeed = new Combo(dialog, SWT.READ_ONLY);
	    comboSpeed.setItems(speeds);
	    
	    Label kmH = new Label(dialog, SWT.NONE); 
	    kmH.setText("km/h"); 
	    
	    //Train Priority
	    
	    Label priority = new Label(dialog, SWT.NONE); 
	    priority.setText("Priorität : "); 
	    
	    comboPriority = new Combo(dialog, SWT.READ_ONLY);
	    comboPriority.setItems(priorities);
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboPriority.setLayoutData(gridData);
	    
	    //Train Ladung
	    
	    Label ladungen = new Label(dialog, SWT.NONE); 
	    ladungen.setText("Ladung : "); 
	    
	    comboLadungen = new Combo(dialog, SWT.READ_ONLY);
	    comboLadungen.setItems(ladung);
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboLadungen.setLayoutData(gridData);
	
	    // Buttonn Composite
	    
	    Composite buttonComposite = new Composite(dialog, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(new Image(null,"img/Ok.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TrainEvents.addNewTrain();
				
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

}
