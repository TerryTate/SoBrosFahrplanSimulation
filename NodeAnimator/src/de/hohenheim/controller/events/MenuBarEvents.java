package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import de.hohenheim.controller.XmlWriter;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.dialouge.AboutUsDialog;
import de.hohenheim.view.dialouge.HelpDialog;
import de.hohenheim.view.dialouge.ProjectAddDialog;
import de.hohenheim.view.dialouge.ProjectEditDialog;
import de.hohenheim.view.dialouge.TimetableAddDialog;
import de.hohenheim.view.dialouge.TimetableDeletDialog;
import de.hohenheim.view.dialouge.TimetableEditDialog;
import de.hohenheim.view.dialouge.TrainAddDialog;
import de.hohenheim.view.dialouge.TrainDeletDialog;
import de.hohenheim.view.dialouge.TrainEditDialog;
import de.hohenheim.view.dialouge.TrainExportDialog;

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
		
		if (Main.trainListAll.size() > 0){
			
			new TrainEditDialog(Main.getShell(), SWT.NONE).open(menu);
			
		}else{
			
			MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
	        messageBox.setMessage("Es gibt keine Züge die Bearbeitet werden können.");    
	        messageBox.open();
			
		}
	    
	}
	
    public static void deletTrain() {
    	
    	if (Main.trainListAll.size() > 0){
		    
    		new TrainDeletDialog(Main.getShell(), SWT.NONE).open(true);
		
        }else{
			
			MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
	        messageBox.setMessage("Es gibt keine Züge die Gelöscht werden können.");    
	        messageBox.open();
			
		}	
	}

	public static void addTimeTable() {
		
		new TimetableAddDialog(Main.getShell(), SWT.NONE).open();
		
	}

	public static void edtitTimetable(boolean menu) {
		
		new TimetableEditDialog(Main.getShell(), SWT.NONE).open(menu);
	}

	public static void deletTimetable(boolean menu) {
		
		new TimetableDeletDialog(Main.getShell(), SWT.NONE).open(menu);
		
	}

	public static void showHelp() {
		
		new HelpDialog(Main.getShell(), SWT.NONE).open();
	}

	public static void save(boolean menu) {
	
		FileDialog fd = new FileDialog(Main.getShell(), SWT.SAVE);
        fd.setText("Zug Exportieren");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.xml" };
        fd.setFilterExtensions(filterExt);
        String selected = fd.open(); 
        
        if (menu == false){
        	
        	TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
        	TrainData td = Main.trainListAll.get(0);
        	int i = 0; 
        	
        	while(Integer.parseInt(rowData[0].getText(0)) != td.getID()){
        		i++;
        		td = Main.trainListAll.get(i);
        	}
        		
        	XmlWriter.saveSingleTrain(selected,  td);
        
        }else if (menu == true){
        	
        	String idCheck = TrainExportDialog.comboTrains.getText();
        	TrainData td = Main.trainListAll.get(0);
        	int i = 0; 
        	
        	while(Integer.parseInt(idCheck) != td.getID()){
        		i++;
        		td = Main.trainListAll.get(i);
        	}
        	
        	XmlWriter.saveSingleTrain(selected,  td);
        	TrainExportDialog.dialog.close();
        }
        
      
        
        System.out.println(selected);
		
	}

	public static void open() {
		
		 FileDialog fd = new FileDialog(Main.getShell(), SWT.OPEN);
	     fd.setText("Zug Importieren");
	     fd.setFilterPath("C:/");
	     String[] filterExt = { "*.xml"};
	     fd.setFilterExtensions(filterExt);
	     String selected = fd.open();
	     XmlWriter.writeToXML(selected);
		
	}

	public static void addProject() {
		
		new ProjectAddDialog(Main.getShell(), SWT.NONE).open();
		
		
	}

	public static void exportTrain() {
		
        if (Main.trainListAll.size() > 0){
        	
        	new TrainExportDialog(Main.getShell(),SWT.NONE).open(true);
    				
        }else{
			
			MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
	        messageBox.setMessage("Es gibt keine Züge die Exportiert werden können.");    
	        messageBox.open();
			
		}	
		
		
	}

	public static void showInfo() {
		
		new AboutUsDialog(Main.getShell(), SWT.NONE).open();
		
	}

	public static void editProject() {
		
		new ProjectEditDialog(Main.getShell(), SWT.NONE).open();
		
	}

}
