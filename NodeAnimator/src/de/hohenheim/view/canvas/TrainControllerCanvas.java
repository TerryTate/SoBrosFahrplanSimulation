package de.hohenheim.view.canvas;



import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import de.hohenheim.controller.events.MenuBarEvents;
import de.hohenheim.controller.events.TrainEvents;



public class TrainControllerCanvas extends Canvas{
	
	String[] typOfTrain = {"S-Bahn", "ICE", "IC", "RegioBahn", "Güterzug", "Dampflock"};
	
	private static Group groupControlSmall;
	
	public TrainControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
	


		// Group 
		
		setGroupControlSmall(new Group(this, SWT.SHADOW_ETCHED_IN));
	    getGroupControlSmall().setText("Zug Verwaltung");
	    getGroupControlSmall().setLayout(new GridLayout());
       
        // Add Button
		Button newTrain = new Button(getGroupControlSmall(), SWT.NONE);
		newTrain.setText("Zug hinzufügen");
	
		newTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 MenuBarEvents.addTrain();
				
			}
		});
		
		 
		Button editTrain = new Button(getGroupControlSmall(), SWT.NONE);
		editTrain.setText("Zug ändern");
		
		editTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 MenuBarEvents.editTrain(false);
				
			}
		});
		
		Button deleteTrain = new Button(getGroupControlSmall(), SWT.NONE);
		deleteTrain.setText("Zug löschen");

		deleteTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
				 TrainEvents.deleteTrain(false);
				
			}
		});
		
		Button importTrain = new Button(getGroupControlSmall(), SWT.NONE);
		importTrain.setText("Zug Importieren");
		
		importTrain.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			
				
			}
		});
		
		Button exportTrain = new Button(getGroupControlSmall(), SWT.NONE);
		exportTrain.setText("Zug Exportieren");
	
		exportTrain.addListener(SWT.Selection, new Listener() {
					
		     public void handleEvent(Event arg0) {
						
					
						
			}
		});
		
		getGroupControlSmall().pack();
		
	}

	
	public static Group getGroupControlSmall() {
		return groupControlSmall;
	}

	public static void setGroupControlSmall(Group groupControlSmall) {
		TrainControllerCanvas.groupControlSmall = groupControlSmall;
	}
		
}
