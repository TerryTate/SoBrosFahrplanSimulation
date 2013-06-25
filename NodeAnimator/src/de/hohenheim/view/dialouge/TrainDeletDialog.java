package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;

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
import de.hohenheim.view.mobile.ImageHelper;

public class TrainDeletDialog extends Dialog {
	
	Shell parent;
	public static Combo comboTrains;
	public static Shell dialog;

	public TrainDeletDialog(Shell parent, int style) {
		super(parent, style);
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
	    
	    dialog.setText("Zug löschen");
	    dialog.setImage(ImageHelper.delete);
	    GridLayout gridLayout = new GridLayout();
	    gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	    
	   
    	Label chooseTrain = new Label(dialog, SWT.NONE);
    	chooseTrain.setText("Wähle Zug ID : ");
    	
    	comboTrains = new Combo(dialog, SWT.READ_ONLY);
    	String[] trainsID = new String [Main.trainListAll.size()];
	    comboTrains.setItems(loadTrainList(trainsID));
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboTrains.setLayoutData(gridData);
	    comboTrains.select(0);
	    
        // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(ImageHelper.ok);
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TrainEvents.deleteTrain(menu);
				
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


	private String[] loadTrainList(String[] trainsID) {
	
		for(int i=0; i < Main.trainListAll.size(); i++) {
			Integer id = Main.trainListAll.get(i).getID();
			trainsID[i] = id.toString();
		}
		
	
		return trainsID;
	}
}
