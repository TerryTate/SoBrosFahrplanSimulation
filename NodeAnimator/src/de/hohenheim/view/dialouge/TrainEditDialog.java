package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeTrain;

public class TrainEditDialog extends Dialog{
	
	Shell parent;
	
	String[] typs = {"S-Bahn", "ICE", "IC", "RegioBahn", "Güterzug", "Dampflock"};
	String[] speeds = {"80", "120", "150", "200", "300"};
	String[] priorities = {"Sehr wichtig", "Wichtig", "Normal", "Irrelevant"};
	String[] ladung = {"keine", "Rohstoffe", "Müll", "Fertige Produkte", "Brennstoffe"};
	String[] trainsID;
	int row;
	
	public static Text idText;
	public static Combo comboTypOfTrain;
	public static Combo comboSpeed;
	public static Combo comboPriority;
	public static Combo comboLadungen;

	private Combo comboTrains;

	public static Shell dialog;

	public TrainEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
		
	}
	
    public void open(final boolean menu) {
		
	    dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(220, 310);
	    dialog.setText("Zug bearbeiten");
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	    
	    TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
	    
	    if (menu == true){
	    	
			System.out.println("Test");
	    	Label chooseTrain = new Label(dialog, SWT.NONE);
	    	chooseTrain.setText("Wähle Zug ID : ");
	    	
	    	comboTrains = new Combo(dialog, SWT.READ_ONLY);
	    	String[] trainsID = new String [Main.trainListAll.size()];
		    comboTrains.setItems(loadTrainList(trainsID));
		    GridData gridData = new GridData();
		    gridData.horizontalSpan = 2;
		    gridData.horizontalAlignment = SWT.FILL;
		    comboTrains.setLayoutData(gridData);
		    
	    	
		}
	    
        // Train ID
	    
	    Label id = new Label(dialog, SWT.NONE); 
	    id.setText("ID : ");
	    
	    idText = new Text(dialog, SWT.NONE);
	   
	    if(menu == false){
	    	
	        idText.setText(rowData[0].getText(0));
	        
	    }else if(menu == true){
	    	
	    	//idText.setText(string);
	    	
	    }
	    
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    idText.setLayoutData(gridData);
	    
	    // Train Typ
	    
	    Label typOfTrain = new Label(dialog, SWT.NONE); 
	    typOfTrain.setText("ZugTyp : "); 
	    
	    comboTypOfTrain = new Combo(dialog, SWT.READ_ONLY);
	    comboTypOfTrain.setItems(typs);
	    
        if(menu == false){
	    	
	        comboTypOfTrain.setText(rowData[0].getText(1));
	        
	    }else if(menu == true){
	    	
	    	//idText.setText(string);
	    	
	    }
	    
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboTypOfTrain.setLayoutData(gridData);

	    //Train Speed
	    
	    Label trainSpeed = new Label(dialog, SWT.NONE); 
	    trainSpeed.setText("Geschwindigkeit : "); 
	    
        comboSpeed = new Combo(dialog, SWT.READ_ONLY);
	    comboSpeed.setItems(speeds);

        if(menu == false){
	    	
	        comboSpeed.setText(rowData[0].getText(2));
	        
	    }else if(menu == true){
	    	
	    	//idText.setText(string);
	    	
	    }
	    
	    Label kmH = new Label(dialog, SWT.NONE); 
	    kmH.setText("km/h"); 
	    
	    //Train Priority
	    
	    Label priority = new Label(dialog, SWT.NONE); 
	    priority.setText("Priorität : "); 
	    
	    comboPriority = new Combo(dialog, SWT.READ_ONLY);
	    comboPriority.setItems(priorities);
	    
        if(menu == false){
	    	
	        comboPriority.setText(rowData[0].getText(3));
	        
	    }else if(menu == true){
	    	
	    	//idText.setText(string);
	    	
	    }
	    
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboPriority.setLayoutData(gridData);
	    
	    //Train Ladung
	    
	    Label ladungen = new Label(dialog, SWT.NONE); 
	    ladungen.setText("Ladung : "); 
	    
	    comboLadungen = new Combo(dialog, SWT.READ_ONLY);
	    comboLadungen.setItems(ladung);
	    
        if(menu == false){
	    	
	        comboLadungen.setText(rowData[0].getText(4));
	        
	    }else if(menu == true){
	    	
	    	//idText.setText(string);
	    	
	    }	    
	    
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
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TrainEvents.editTrain(menu);
				
			}
		});
	    
	    // Cancel Button
		
		Button cancelButton = new Button(dialog, SWT.NONE);
		cancelButton.setText("Cancel");
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

	private void getRow(int row2) {
		// TODO Auto-generated method stub
		
	}

	private String[] loadTrainList(String[] trainsID2) {
		
		
		for(int i=0; i < Main.trainListAll.size(); i++) {
			Integer id = Main.trainListAll.get(i).getID();
			trainsID2[i] = id.toString();
		}
		
	
		return trainsID2;
	}

}
