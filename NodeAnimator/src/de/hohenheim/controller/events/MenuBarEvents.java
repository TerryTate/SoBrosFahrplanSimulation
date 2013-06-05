package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;

import de.hohenheim.controller.XmlWriter;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.dialouge.HelpDialog;
import de.hohenheim.view.dialouge.TimetableAddDialog;
import de.hohenheim.view.dialouge.TimetableDeletDialog;
import de.hohenheim.view.dialouge.TimetableEditDialog;
import de.hohenheim.view.dialouge.TrainAddDialog;
import de.hohenheim.view.dialouge.TrainDeletDialog;
import de.hohenheim.view.dialouge.TrainEditDialog;


public class MenuBarEvents {

	public static void closeProgramm() {
		
		 MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ICON_QUESTION
		            | SWT.YES | SWT.NO);
		 messageBox.setMessage("Wollen Sie das Programm wirklich beenden ?");
		 messageBox.setText("Programm Beenden");
		 int response = messageBox.open();
		 if (response == SWT.YES)
		 System.exit(0);
		
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
	    
		new TrainDeletDialog(Main.getShell(), SWT.NONE).open(true);
		
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

	public static void showHelp() {
		
		new HelpDialog(Main.getShell(), SWT.NONE).open();
	}

	public static void saveTrain() {
	
		FileDialog fd = new FileDialog(Main.getShell(), SWT.SAVE);
        fd.setText("Zug Exportieren");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.xml" };
        fd.setFilterExtensions(filterExt);
        String selected = fd.open(); 
        XmlWriter.writeToXML(selected);
        
        System.out.println(selected);
		
	}

	public static void openTrain() {
		
		 FileDialog fd = new FileDialog(Main.getShell(), SWT.OPEN);
	     fd.setText("Zug Importieren");
	     fd.setFilterPath("C:/");
	     String[] filterExt = { "*.xml"};
	     fd.setFilterExtensions(filterExt);
	     String selected = fd.open();
	     XmlWriter.writeToXML(selected);
		
	}

}
