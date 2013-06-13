package de.hohenheim.view.canvas;

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Spinner;

import de.hohenheim.controller.events.AnimationEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.State;
import de.hohenheim.modell.Train;
import de.hohenheim.view.map.NodeMap;
import de.hohenheim.view.mobile.AnimationFigure;
import de.hohenheim.view.mobile.TrainFigure;

public class AnimationControllerCanvas extends Canvas{

	private static NodeMap map;
	public static Combo comboProjects;
	private Spinner houre;
	private Spinner minutes;
	private Combo comboDrivingday;

	public AnimationControllerCanvas(Composite parent, int style, NodeMap map) {
		super(parent, style);
		this.map = map;
		createContents();
	}

	private void createContents() {
		Group group = new Group(this, SWT.SHADOW_ETCHED_IN);
	    group.setText("Animations Einstellungen");
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
	    group.setLayout(gridLayout);
	    
	    //Project
	    
		Label choseProject = new Label(group, SWT.NONE);
		choseProject.setText("Project ID wählen:");
		
		comboProjects = new Combo(group, SWT.READ_ONLY);
		String[] projectIDs = new String [Main.projectListAll.size()];
		comboProjects.setItems(loadProjectIDs(projectIDs));
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		comboProjects.setLayoutData(gridData);
		comboProjects.addSelectionListener(new SelectionListener() {
		       
	    	public void widgetSelected(SelectionEvent e) {
	          
	    	    AnimationEvents.drawTrains(map, Integer.parseInt(comboProjects.getText()));
	    		
	        }

	        public void widgetDefaultSelected(SelectionEvent e) {
	         
	        }
	      });
		
		//Time
		
		Label starttime = new Label(group, SWT.NONE);
		starttime.setText("Start Uhrzeit : ");
		    
		Composite timeComposite = new Composite(group, SWT.NONE);
	    timeComposite.setLayout(new FillLayout());
	    gridData = new GridData();
    	gridData.horizontalSpan = 2;
	    timeComposite.setLayoutData(gridData);
		    
		houre = new Spinner(timeComposite, SWT.NONE);
		houre.setMaximum(23);
		    
		Label h = new Label(timeComposite, SWT.NONE);
		h.setText("  h");
		    
		minutes = new Spinner(timeComposite, SWT.NONE);
		minutes.setMaximum(59);
		    
		Label m = new Label(timeComposite, SWT.NONE);
		m.setText("  m");
		
		//
		
		Label drivingday = new Label(group, SWT.NONE);
		drivingday.setText("Fahrtag wählen:");
		
		comboDrivingday = new Combo(group, SWT.READ_ONLY);
		
		//comboProjects.setItems(loadProjectIDs(projectIDs));
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		comboDrivingday.setLayoutData(gridData);
		
		//
		
		Label animationSpeed= new Label(group, SWT.NONE);
		animationSpeed.setText("Animationsgeschwindigkeit:");
		
		Scale scaleAnimationSpeed = new Scale(group, SWT.HORIZONTAL);
		scaleAnimationSpeed.setMinimum(0);
		scaleAnimationSpeed.setMaximum(100);
	
		
		group.pack();
		
	}
	public static String[] loadProjectIDs(String[] projectIDs) {
	    
		for(int i=0; i < Main.projectListAll.size(); i++) {
			Integer id = Main.projectListAll.get(i).getId();
			projectIDs[i] = id.toString();
		}
		
	
		return projectIDs;
	}

	public static String[] getNodeNames() {
		Object[] names = map.getNodes().keySet().toArray();
		String[] n = new String[names.length];
		for(int i=0; i<names.length; i++) {
			n[i]=names[i].toString();
		}
		bubblesort(n);
		//Arrays.sort(n);
		return n;
	}
	
	private static void bubblesort(String[] n) {
	boolean unsortiert=true;
	String temp;
	
	while (unsortiert){
		unsortiert = false;
		for (int i=0; i < n.length-1; i++){
		    if(Integer.parseInt(n[i]) >= Integer.parseInt(n[i+1])){
			    temp = n[i];
			    n[i] = n[i+1];
			    n[i+1] = temp;
			    unsortiert = true;
			
		    }
		}    
	}
	}
	
	private String[] getMobileObjects() {
		String[] s = new String[map.getMobileObjects().size()];
		map.getMobileObjects().keySet().toArray(s);
		Arrays.sort(s);
		return s;
	}
	

}
