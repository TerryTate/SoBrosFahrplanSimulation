package de.hohenheim.view.dialouge;



import java.awt.Dimension;
import java.awt.Toolkit;

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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.mobile.ImageHelper;

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
    String message = "";
	
	public TrainAddDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
		
	}

	public void open() {

		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(300, 240);
	    
	    //Fenster  mittig setzen 
	    Toolkit myToolkit = Toolkit.getDefaultToolkit();
	    Dimension myDimension = myToolkit.getScreenSize();
	    dialog.setLocation((int) ((myDimension.getWidth() - dialog.getSize().x) / 2), 
	    		           (int) ((myDimension.getHeight() - dialog.getSize().y) / 2));
	    
	    dialog.setText("Zug erstellen");
	    dialog.setImage(ImageHelper.add);;
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
	    idText.setTextLimit(6);
	    
	    // Train Typ
	    
	    Label typOfTrain = new Label(dialog, SWT.NONE); 
	    typOfTrain.setText("ZugTyp : "); 
	    
	    comboTypOfTrain = new Combo(dialog, SWT.READ_ONLY);
	    comboTypOfTrain.setItems(typs);
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboTypOfTrain.setLayoutData(gridData);
	    comboTypOfTrain.select(0);

	    //Train Speed
	    
	    Label trainSpeed = new Label(dialog, SWT.NONE); 
	    trainSpeed.setText("Geschwindigkeit : "); 
	    
        comboSpeed = new Combo(dialog, SWT.READ_ONLY);
	    comboSpeed.setItems(speeds);
	    comboSpeed.select(0);
	    
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
	    comboPriority.select(0);
	    
	    //Train Ladung
	    
	    Label ladungen = new Label(dialog, SWT.NONE); 
	    ladungen.setText("Ladung : "); 
	    
	    comboLadungen = new Combo(dialog, SWT.READ_ONLY);
	    comboLadungen.setItems(ladung);
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboLadungen.setLayoutData(gridData);
	    comboLadungen.select(0);
	    
	    // Buttonn Composite
	    
	    Composite buttonComposite = new Composite(dialog, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(ImageHelper.ok);
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				if (trainIdCheck()){
					TrainEvents.addNewTrain();
				}else{
					
					MessageBox messageBox = new MessageBox(dialog, SWT.ERROR | SWT.OK);
			        messageBox.setMessage(message);    
			        messageBox.open();
				}
						
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
	
	protected boolean trainIdCheck() {
		message = "";	
		boolean idCheck = true;
		try{
		    int id = Integer.parseInt(idText.getText());
		    if (id < 0){
		    	message = message + "Die Zug-ID muss eine positive Zahl sein!\n"+"\r\n";
		    	idCheck = false;
		    }
		    for(int j = 0; j < Main.trainListAll.size(); j++){
			    if(id == Main.trainListAll.get(j).getID()){
			    
			        message = message + "Diese Zug-ID ist bereits vorhanden. " +
				    			"Bitte geben Sie eine andere, 1 bis 6-stellige Ziffer ein \n" +
				        		"und versuchen Sie es erneut.\n"+"\r\n";
			    	idCheck = false;
			    }
		    }
		
		}catch(NumberFormatException e){
			message = message + "Die Zug-ID darf nur aus Zahlen bestehen\n" +
	        					"und muss mindestens eine Ziffer haben!\n"+"\r\n";
			
			idCheck = false;
		}
		 
		return idCheck;
	}

}
