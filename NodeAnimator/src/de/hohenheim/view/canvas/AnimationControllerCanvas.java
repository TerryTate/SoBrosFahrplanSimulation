package de.hohenheim.view.canvas;

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.State;
import de.hohenheim.modell.Train;
import de.hohenheim.view.map.NodeMap;
import de.hohenheim.view.mobile.AnimationFigure;
import de.hohenheim.view.mobile.TrainFigure;

public class AnimationControllerCanvas extends Canvas{

	private static NodeMap map;

	public AnimationControllerCanvas(Composite parent, int style, NodeMap map) {
		super(parent, style);
		this.map = map;
		createContents();
	}

	private void createContents() {
		Group group = new Group(this, SWT.SHADOW_ETCHED_IN);
	    group.setText("Animations Einstellungen");

	    //Nodes, e.g. rooms or haltestellen or anything
		Label choseProject = new Label(group, SWT.NONE);
		choseProject.setText("Project ID wählen:");
		
		final Combo comboProjects = new Combo(group, SWT.READ_ONLY);
		String[] projectIDs = new String [Main.projectListAll.size()];
		comboProjects.setItems(loadProjectIDs(projectIDs));
		
		//Trains
		Label train_label = new Label(group, SWT.NONE);
		train_label.setText("Zug");
		train_label .setBounds(5,75,150, 15);
	    final Combo train_combo = new Combo(group, SWT.READ_ONLY);
	    train_combo.setBounds(5,90,150, 25);
		String[] fitems = getMobileObjects();
		train_combo.setItems(fitems);
		
		Button walkto = new Button(group, SWT.NONE);
		walkto.setBounds(5, 120, 150,25);
		walkto.setText("Go To");
		walkto.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
//				int i_node = node_combo.getSelectionIndex();
//				String node = node_combo.getItem(i_node);
				int i_mobile = train_combo.getSelectionIndex();
				String train = train_combo.getItem(i_mobile);				
				AnimationFigure fig = map.getMobileObjects().get(train);
				if(fig instanceof TrainFigure) {
					TrainFigure f = (TrainFigure)fig;
					Train ft = (Train)f.getModelObject(); 	
					f.stopAnimation();
					f.clearAnimations();
					Iterator<State> it = ft.getBlockedStates();
					while(it.hasNext()) {
						it.next().setState(State.UNBLOCKED);	
					}
					
//					f.waitFor(State.statemap.get(node));					
//					f.walkTo(map.getNodes().get(node));					
					f.startAnimation();
				}
			}
		});
		
		
		
		group.pack();
		
	}
	private String[] loadProjectIDs(String[] projectIDs) {
	    
		for(int i=0; i < Main.trainListAll.size(); i++) {
			Integer id = Main.trainListAll.get(i).getID();
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
		//bubblesort(n);
		Arrays.sort(n);
		return n;
	}
	
//	private static void bubblesort(String[] n) {


	private String[] getMobileObjects() {
		String[] s = new String[map.getMobileObjects().size()];
		map.getMobileObjects().keySet().toArray(s);
		Arrays.sort(s);
		return s;
	}
	

}
