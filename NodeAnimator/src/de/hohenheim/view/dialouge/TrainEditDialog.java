package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeTrain;

public class TrainEditDialog extends Dialog{
	
	Shell parent;
	
	String[] typs = {"S-Bahn", "ICE", "IC", "RegioBahn", "G�terzug", "Dampflock"};
	String[] speeds = {"80", "120", "150", "200", "300"};
	String[] priorities = {"Sehr wichtig", "Wichtig", "Normal", "Irrelevant"};
	String[] ladung = {"keine", "Rohstoffe", "M�ll", "Fertige Produkte", "Brennstoffe"};
	String[] trainsID;
	int row;
	
	public static Text idText;
	public static Combo comboTypOfTrain;
	public static Combo comboSpeed;
	public static Combo comboPriority;
	public static Combo comboLadungen;

	private Combo comboTrains;

	public static Shell trainEdit;

	public TrainEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
		
	}
	
    public void open(final boolean menu) {
		
	    trainEdit = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    trainEdit.setSize(320, 310);
	    trainEdit.setLocation(500, 200);
	    trainEdit.setText("Zug bearbeiten");
	    trainEdit.setImage(new Image(null, "img/Edit2.png"));
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    trainEdit.setLayout(gridLayout);
	    
	    TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
	    
	    if (menu == true){
	    	
			System.out.println("Test");
	    	Label chooseTrain = new Label(trainEdit, SWT.NONE);
	    	chooseTrain.setText("W�hle Zug ID : ");
	    	
	    	comboTrains = new Combo(trainEdit, SWT.READ_ONLY);
	    	String[] trainsID = new String [Main.trainListAll.size()];
		    comboTrains.setItems(loadTrainList(trainsID));
		    
		    comboTrains.addSelectionListener(new SelectionListener() {
		       
		    	public void widgetSelected(SelectionEvent e) {
		          
		    	    setText();
		    		
		        }

		        public void widgetDefaultSelected(SelectionEvent e) {
		         
		        }
		      });
		    
		    GridData gridData = new GridData();
		    gridData.horizontalSpan = 2;
		    gridData.horizontalAlignment = SWT.FILL;
		    comboTrains.setLayoutData(gridData);
		    
	    	
		}
	    
        // Train ID
	    
	    Label id = new Label(trainEdit, SWT.NONE); 
	    id.setText("ID : ");
	    
	    idText = new Text(trainEdit, SWT.NONE);
	     
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    idText.setLayoutData(gridData);
	    
	    // Train Typ
	    
	    Label typOfTrain = new Label(trainEdit, SWT.NONE); 
	    typOfTrain.setText("ZugTyp : "); 
	    
	    comboTypOfTrain = new Combo(trainEdit, SWT.READ_ONLY);
	    comboTypOfTrain.setItems(typs);    
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboTypOfTrain.setLayoutData(gridData);

	    //Train Speed
	    
	    Label trainSpeed = new Label(trainEdit, SWT.NONE); 
	    trainSpeed.setText("Geschwindigkeit : "); 
	    
        comboSpeed = new Combo(trainEdit, SWT.READ_ONLY);
	    comboSpeed.setItems(speeds);
	    
	    Label kmH = new Label(trainEdit, SWT.NONE); 
	    kmH.setText("km/h"); 
	    
	    //Train Priority
	    
	    Label priority = new Label(trainEdit, SWT.NONE); 
	    priority.setText("Priorit�t : "); 
	    
	    comboPriority = new Combo(trainEdit, SWT.READ_ONLY);
	    comboPriority.setItems(priorities);  
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboPriority.setLayoutData(gridData);
	    
	    //Train Ladung
	    
	    Label ladungen = new Label(trainEdit, SWT.NONE); 
	    ladungen.setText("Ladung : "); 
	    
	    comboLadungen = new Combo(trainEdit, SWT.READ_ONLY);
	    comboLadungen.setItems(ladung);
	    
        if(menu == false){
	    	
        	idText.setText(rowData[0].getText(0));
        	comboTypOfTrain.setText(rowData[0].getText(1));
        	comboSpeed.setText(rowData[0].getText(2));
        	comboPriority.setText(rowData[0].getText(3));
	        comboLadungen.setText(rowData[0].getText(4));
	        
	    }   
	    
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboLadungen.setLayoutData(gridData);
	
	    // Buttonn Composite
	    
	    Composite buttonComposite = new Composite(trainEdit, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(trainEdit, SWT.NONE);
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
		
		Button cancelButton = new Button(trainEdit, SWT.NONE);
		cancelButton.setText("Cancel");
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    cancelButton.setLayoutData(gridData);
		
		cancelButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			   trainEdit.close();
				
			}
		});
	    
	    trainEdit.open();
	}


	protected void setText() {
		
		for(int i = 0; i < Main.trainListAll.size(); i++ ){
			
			TrainData td = Main.trainListAll.get(i);
			
			if (Integer.valueOf(comboTrains.getText()) == td.getID()){
				
				Integer id = Integer.valueOf(td.getID());
				Integer speed = Integer.valueOf(td.getSpeed());
				
				idText.setText(id.toString());
	        	comboTypOfTrain.setText(td.getTypOfTrain());
	        	comboSpeed.setText(speed.toString());
	        	comboPriority.setText(td.getPriority());
		        comboLadungen.setText(td.getLadung());
					
			}
		}
		
	}

	private String[] loadTrainList(String[] trainsID2) {
		
		
		for(int i=0; i < Main.trainListAll.size(); i++) {
			Integer id = Main.trainListAll.get(i).getID();
			trainsID2[i] = id.toString();
		}
		
	
		return trainsID2;
	}

}
