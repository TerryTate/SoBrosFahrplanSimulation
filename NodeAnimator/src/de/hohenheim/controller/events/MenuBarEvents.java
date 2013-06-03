package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;

import de.hohenheim.controller.main.Main;
import de.hohenheim.view.dialouge.TimetableAddDialog;
import de.hohenheim.view.dialouge.TimetableDeletDialog;
import de.hohenheim.view.dialouge.TimetableEditDialog;
import de.hohenheim.view.dialouge.TrainAddDialog;
import de.hohenheim.view.dialouge.TrainDeletDialog;
import de.hohenheim.view.dialouge.TrainEditDialog;


public class MenuBarEvents {

	public static void closeProgramm() {
		
		Main.getShell().close();
		
		// Nicht sicher ob man das einfach weglassen kann  
		//Main.getShell().getDisplay().dispose();
		
	}

	public static void changeLookToAnimation() {
		
		Main.getcTabFolder().setSelection(0);
		
	}

	public static void changeLookToProject() {
		
		Main.getcTabFolder().setSelection(1);
		
	}

	public static void changeLookToTrain() {
		
		Main.getcTabFolder().setSelection(2);
		
	}

	public static void changeLookToTimetable() {
		
		Main.getcTabFolder().setSelection(3);
		
	}

	public static void addTrain() {
		
		new TrainAddDialog(Main.getShell(), SWT.NONE).open();
		
	}

	public static void editTrain(boolean menu) {
	    
		new TrainEditDialog(Main.getShell(), SWT.NONE).open(menu);
		
	}
	
    public static void deletTrain() {
	    
		new TrainDeletDialog(Main.getShell(), SWT.NONE).open();
		
	}

	public static void addTimeTable() {
		
		new TimetableAddDialog(Main.getShell(), SWT.NONE).open();
		
	}

	public static void edtitTimetable() {
		
		new TimetableEditDialog(Main.getShell(), SWT.NONE).open();
	}

	public static void deletTimetable() {
		
		new TimetableDeletDialog(Main.getShell(), SWT.NONE).open();
		
	}

}
