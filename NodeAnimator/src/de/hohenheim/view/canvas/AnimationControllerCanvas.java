package de.hohenheim.view.canvas;

import java.util.Arrays;

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
import de.hohenheim.controller.events.AnimationPlay;
import de.hohenheim.controller.main.Main;

import de.hohenheim.modell.project.Project;
import de.hohenheim.view.map.NodeMap;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * 
 * @author Arthur Kaul
 *
 */
public class AnimationControllerCanvas extends Canvas{

	public static NodeMap map;
	public static Combo comboProjects;
	public static Spinner houre;
	public static Spinner minutes;
	public static Combo comboDrivingday;
	String [] drivingdayItems= {"Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"};
	public static Label timer;
	public static boolean run = false; 
	public static int hour;
	public static int min;
	
	/**
	 * 
	 * @param parent
	 * @param style
	 * @param map
	 */
	public AnimationControllerCanvas(Composite parent, int style, NodeMap map) {
		super(parent, style);
		this.map = map;
		createContents();
	}

	/**
	 * 
	 * 
	 */
	private void createContents() {
		GridLayout gridLayout1 = new GridLayout ();
		gridLayout1.numColumns = 1; 
		
		this.setLayout(gridLayout1);
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
		comboProjects.select(0);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		comboProjects.setLayoutData(gridData);
		comboProjects.addSelectionListener(new SelectionListener() {
		       
	    	public void widgetSelected(SelectionEvent e) {
	          
	    	   // AnimationEvents.drawTrains(map, Integer.parseInt(comboProjects.getText()));
	    		
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
		
		// Drivingday
		
		Label drivingday = new Label(group, SWT.NONE);
		drivingday.setText("Fahrtag wählen:");
		
		comboDrivingday = new Combo(group, SWT.READ_ONLY);
		
		comboDrivingday.setItems(drivingdayItems);
		comboDrivingday.select(0);
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		comboDrivingday.setLayoutData(gridData);
		
		// Animation Speed 
		
		Label animationSpeed= new Label(group, SWT.NONE);
		animationSpeed.setText("Geschwindigkeit : ");
		
		Scale scaleAnimationSpeed = new Scale(group, SWT.HORIZONTAL);
		gridData = new GridData ();
		gridData.horizontalSpan = 2;
		scaleAnimationSpeed.setLayoutData(gridData);
		scaleAnimationSpeed.setMinimum(0);
		scaleAnimationSpeed.setMaximum(50);
		
		group.pack();
		
		
		Group group2 = new Group(this, SWT.SHADOW_ETCHED_IN | SWT.FILL);
	    group2.setText("Animations Einstellungen");
        GridLayout gridLayout2 = new GridLayout();
        gridLayout2.numColumns = 3;
        
	    group2.setLayout(gridLayout2);
		
		timer = new Label(group2, SWT.NONE);
		setTimerLabel();
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = SWT.CENTER;
	
		timer.setLayoutData(gridData);
	
		Button play = new Button(group2, SWT.NONE);
		play.setImage(ImageHelper.play48);
		play.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
			   
				if (run == false){
					setTimerLabel();
					run = true;
				
					Project p = null;
					for (Project project : Main.projectListAll) {
							
						if (Integer.parseInt(comboProjects.getText()) == project.getId()){
							
							p = project;
						}
							
					}
					AnimationPlay animationPlay = new AnimationPlay(Integer.parseInt(comboProjects.getText()), comboDrivingday.getText(), map, Integer.parseInt(houre.getText()), Integer.parseInt(minutes.getText()), p);
					hour = Integer.parseInt(houre.getText());
					min = Integer.parseInt(minutes.getText());  
					animationPlay.start();
			
			    }
				
			}
		});
		
		
		Button pause = new Button(group2, SWT.NONE);
		pause.setImage(ImageHelper.pause48);
		pause.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
			   
				
				
			}
		});
		
		Button stop = new Button(group2, SWT.NONE);
		stop.setImage(ImageHelper.stop48);
		stop.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
			   
				
			}
		});
		
	   
		
	}
	
	/**
	 * 
	 * 
	 */
	private void setTimerLabel() {
		
		if ((Integer.parseInt(houre.getText()) < 10) && Integer.parseInt(minutes.getText()) < 10){
			timer.setText("Timer : " + "0" + houre.getText() + " : " + "0" + minutes.getText());
		}else if ((Integer.parseInt(houre.getText()) >= 10) && Integer.parseInt(minutes.getText()) < 10){
			timer.setText("Timer : " + houre.getText() + " : " + "0" + minutes.getText());
		}else if ((Integer.parseInt(houre.getText()) >= 10) && Integer.parseInt(minutes.getText()) >= 10){
			timer.setText("Timer : " + houre.getText() + " : " + minutes.getText());
		}else if ((Integer.parseInt(houre.getText()) < 10) && Integer.parseInt(minutes.getText()) >= 10){
			timer.setText("Timer : " + "0" + houre.getText() + " : " + minutes.getText());
		}
		
	}

	/**
	 * 
	 * @param projectIDs
	 * @return
	 */
	public static String[] loadProjectIDs(String[] projectIDs) {
	    
		for(int i=0; i < Main.projectListAll.size(); i++) {
			Integer id = Main.projectListAll.get(i).getId();
			projectIDs[i] = id.toString();
		}
		
	
		return projectIDs;
	}

	/**
	 * 
	 * @return
	 */
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
	
	/**
	 * 
	 * @param n
	 */
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
