package de.hohenheim.view.canvas;



import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.view.composite.CompositeTrain;


public class TrainControllerCanvas extends Canvas{
	
	String[] typOfTrain = {"S-Bahn", "ICE", "IC", "RegioBahn", "Güterzug", "Dampflock"};
	
	//for add new Train
	private static Text textID;
	private static Text textSpeed;
	private static Text textTypOfTrain;
	private static Combo typOfTrain_combo;
	
	// for Edit Text
	private static Text textID2;
	private static Text textSpeed2;
	private static Text textTypOfTrain2;
	private static Combo typOfTrain_combo2;
	
	// different Groups
	private static Group groupAddTrain;
	private static Group groupControlSmall;
	private static Group groupEditTrain;
	private static Group groupDeletTrain;
	private static Group groupImportTrain;
	private static Group groupExportTrain;
	
	
	public TrainControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
	
		// Group with all controllers for add a new Train
		
		setGroupAddTrain(new Group(this, SWT.SHADOW_ETCHED_IN));
	    getGroupAddTrain().setText("Zug hinzufügen");
        GridLayout gridLayout = new GridLayout(); 
        gridLayout.numColumns = 3; 
	    getGroupAddTrain().setLayout(gridLayout);
        
	        //Train ID
	    
	    Label iD = new Label(getGroupAddTrain(), SWT.NONE); 
	    GridData gridData = new GridData();
	    gridData.horizontalAlignment = SWT.END;
	    iD.setText("ID : ");
	    iD.setLayoutData(gridData);
	    
	    setTextID(new Text(getGroupAddTrain(), SWT.BORDER)); 
	    getTextID().setText(""); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 2;
	    getTextID().setTextLimit(6);
	    getTextID().setLayoutData(gridData);
	    
            //TypOfTrain
	    
	    Label label_typOfTrain = new Label(getGroupAddTrain(), SWT.NONE); 
	    label_typOfTrain.setText("ZugTyp : "); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.END;
	    label_typOfTrain.setLayoutData(gridData);
	    
	    setTypOfTrain_combo(new Combo(getGroupAddTrain(), SWT.READ_ONLY));
		getTypOfTrain_combo().setBounds(5,45,150, 25);
		getTypOfTrain_combo().setItems(typOfTrain);
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 2; 
	    getTypOfTrain_combo().setLayoutData(gridData);
	
	        //TrainSpeed
	    
	    Label trainSpeed = new Label(getGroupAddTrain(), SWT.NONE); 
	    trainSpeed.setText("Geschwindigkeit : "); 
	    
	    setTextSpeed(new Text(getGroupAddTrain(), SWT.BORDER)); 
	    getTextSpeed().setText(""); 
	    getTextID().setLayoutData(gridData);
	    
	    Label kmH = new Label(getGroupAddTrain(), SWT.NONE); 
	    kmH.setText("km/h"); 
	    
	       // Add Button
	    
		Button newTrain = new Button(getGroupAddTrain(), SWT.NONE);
		newTrain.setText("Zug hinzufügen");
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    gridData.horizontalSpan = 3; 
	    newTrain.setLayoutData(gridData);
		
		newTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				TrainEvents.addNewTrain();
				
			}
		});
		
		getGroupAddTrain().pack();
		
	    // Group with all controllers for edit a existing Train
		
		setGroupEditTrain(new Group(this, SWT.SHADOW_ETCHED_IN ));
	    getGroupEditTrain().setText("Zug bearbeiten");
	    getGroupEditTrain().setBounds(0, 135, 0, 0);
	    getGroupEditTrain().setLayout(gridLayout);
        
	        //Train ID edit
	    
	    Label iD2 = new Label(getGroupEditTrain(), SWT.NONE); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.END;
	    iD2.setText("ID : ");
	    iD2.setLayoutData(gridData);
	    
	    setTextID2((new Text(getGroupEditTrain(), SWT.BORDER))); 
	    getTextID2().setText(""); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 2;
	    getTextID2().setTextLimit(6);
	    getTextID2().setLayoutData(gridData);
	    
            //TypOfTrain
	    
	    Label label_typOfTrain2 = new Label(getGroupEditTrain(), SWT.NONE); 
	    label_typOfTrain2.setText("ZugTyp : "); 
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.END;
	    label_typOfTrain2.setLayoutData(gridData);
	    
	    typOfTrain_combo2 = (new Combo(getGroupEditTrain(), SWT.READ_ONLY));
	    typOfTrain_combo2.setBounds(5,45,150, 25);
	    typOfTrain_combo2.setItems(typOfTrain);
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 2; 
	    typOfTrain_combo2.setLayoutData(gridData);
	    
	        //TrainSpeed edit
	    
	    Label trainSpeed2 = new Label(getGroupEditTrain(), SWT.NONE); 
	    trainSpeed2.setText("Geschwindigkeit : "); 
	    
	    setTextSpeed2((new Text(getGroupEditTrain(), SWT.BORDER))); 
	    getTextSpeed2().setText(""); 
	    
	    Label kmH2 = new Label(getGroupEditTrain(), SWT.NONE); 
	    kmH2.setText("km/h"); 
	     
		Button editTrain = new Button(getGroupEditTrain(), SWT.NONE);
		editTrain.setText("Zug ändern");
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    gridData.horizontalSpan = 3; 
	    editTrain.setLayoutData(gridData);
		
		editTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				

				
			}
		});
		
		getGroupEditTrain().pack();
		
        // Group with all controllers for delete a existing Train
		
		setGroupDeletTrain((new Group(this, SWT.SHADOW_ETCHED_IN )));
		getGroupDeletTrain().setText("Zug löschen");
		getGroupDeletTrain().setBounds(0, 265, 0, 0);
		getGroupDeletTrain().setLayout(gridLayout);
		
		Button deleteTrain = new Button(getGroupDeletTrain(), SWT.NONE);
		deleteTrain.setText("Zug löschen");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTrain.setLayoutData(gridData);
		deleteTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		getGroupDeletTrain().pack();
		
		//Group with all controllers for import a Train
		
		setGroupImportTrain((new Group(this, SWT.SHADOW_ETCHED_IN )));
		getGroupImportTrain().setText("Zug Importieren");
		getGroupImportTrain().setBounds(0, 317, 400, 0);
		getGroupImportTrain().setLayout(gridLayout);
		
		Button importTrain = new Button(getGroupImportTrain(), SWT.NONE);
		importTrain.setText("Zug Importieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importTrain.setLayoutData(gridData);
		importTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		getGroupImportTrain().pack();
		
		//Group with all controllers for export a Train
		
		setGroupExportTrain((new Group(this, SWT.SHADOW_ETCHED_IN )));
		getGroupExportTrain().setText("Zug Exportieren");
		getGroupExportTrain().setBounds(0, 370, 0, 0);
		getGroupExportTrain().setLayout(gridLayout);
				
		Button exportTrain = new Button(getGroupExportTrain(), SWT.NONE);
		exportTrain.setText("Zug Exportieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTrain.setLayoutData(gridData);
		exportTrain.addListener(SWT.Selection, new Listener() {
					
		     public void handleEvent(Event arg0) {
						
					
						
			}
		});
		
		getGroupExportTrain().pack();
		
		// Group when the shellHeight < then 300 pixel
		
		setGroupControlSmall(new Group(this, SWT.SHADOW_ETCHED_IN));
	    getGroupControlSmall().setText("Zug Verwaltung");
        GridLayout gridLayout3 = new GridLayout(); 
        gridLayout.numColumns = 1; 
	    getGroupControlSmall().setLayout(gridLayout3);
        
	 

		Button newTrain2 = new Button(getGroupControlSmall(), SWT.NONE);
		newTrain2.setText("Zug hinzufügen");
	
		
		newTrain2.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		 
		Button editTrain2 = new Button(getGroupControlSmall(), SWT.NONE);
		editTrain2.setText("Zug ändern");
		gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    gridData.horizontalAlignment = SWT.FILL;
	    gridData.horizontalSpan = 3; 
	    editTrain2.setLayoutData(gridData);
		
		editTrain2.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
		
				
			}
		});
		
		Button deleteTrain2 = new Button(getGroupControlSmall(), SWT.NONE);
		deleteTrain2.setText("Zug löschen");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTrain2.setLayoutData(gridData);
		deleteTrain2.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		Button importTrain2 = new Button(getGroupControlSmall(), SWT.NONE);
		importTrain2.setText("Zug Importieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importTrain2.setLayoutData(gridData);
		importTrain2.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		Button exportTrain2 = new Button(getGroupControlSmall(), SWT.NONE);
		exportTrain2.setText("Zug Exportieren");
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTrain2.setLayoutData(gridData);
		exportTrain2.addListener(SWT.Selection, new Listener() {
					
		     public void handleEvent(Event arg0) {
						
					
						
			}
		});
		
		getGroupControlSmall().pack();
		getGroupControlSmall().setVisible(false);
	}

	/*
	 * Getter and Setter for add Train
	 * 
	 */
	
	public static Text getTextID() {
		return textID;
	}

	public static void setTextID(Text textID) {
		TrainControllerCanvas.textID = textID;
	}
    
	public static Text getTextSpeed() {
		return textSpeed;
	}

	public static void setTextSpeed(Text textSpeed) {
		TrainControllerCanvas.textSpeed = textSpeed;
	}

	public static Text getTextTypOfTrain() {
		return textTypOfTrain;
	}

	public static void setTextTypOfTrain(Text textTypeOfTrain) {
		TrainControllerCanvas.textTypOfTrain = textTypeOfTrain;
	}
	
	public static Combo getTypOfTrain_combo() {
		return typOfTrain_combo;
	}

	public static void setTypOfTrain_combo(Combo typOfTrain_combo) {
		TrainControllerCanvas.typOfTrain_combo = typOfTrain_combo;
	}
	
	/*
     *   Getter and Setter for the different groups 
     * 
     */
	
	public static Group getGroupControlSmall() {
		return groupControlSmall;
	}

	public static void setGroupControlSmall(Group groupControlSmall) {
		TrainControllerCanvas.groupControlSmall = groupControlSmall;
	}

	public static Group getGroupAddTrain() {
		return groupAddTrain;
	}

	public static void setGroupAddTrain(Group groupAddTrain) {
		TrainControllerCanvas.groupAddTrain = groupAddTrain;
	}

	public static Group getGroupEditTrain() {
		return groupEditTrain;
	}

	public static void setGroupEditTrain(Group groupEditTrain) {
		TrainControllerCanvas.groupEditTrain = groupEditTrain;
	}
    
	/*
	 *  Getter and Setter for edit a Train 
	 * 
	 */
	
	public static Text getTextID2() {
		return textID2;
	}

	public static void setTextID2(Text textID2) {
		TrainControllerCanvas.textID2 = textID2;
	}

	public static Text getTextSpeed2() {
		return textSpeed2;
	}

	public static void setTextSpeed2(Text textSpeed2) {
		TrainControllerCanvas.textSpeed2 = textSpeed2;
	}

	public static Combo getTypOfTrain_combo2() {
		return typOfTrain_combo2;
	}

	public static void setTypOfTrain_combo2(Combo typOfTrain_combo2) {
		TrainControllerCanvas.typOfTrain_combo2 = typOfTrain_combo2;
	}

	public static Group getGroupDeletTrain() {
		return groupDeletTrain;
	}

	public static void setGroupDeletTrain(Group groupDeletTrain) {
		TrainControllerCanvas.groupDeletTrain = groupDeletTrain;
	}

	public static Group getGroupImportTrain() {
		return groupImportTrain;
	}

	public static void setGroupImportTrain(Group groupImportTrain) {
		TrainControllerCanvas.groupImportTrain = groupImportTrain;
	}

	public static Group getGroupExportTrain() {
		return groupExportTrain;
	}

	public static void setGroupExportTrain(Group groupExportTrain) {
		TrainControllerCanvas.groupExportTrain = groupExportTrain;
	}
	
}
