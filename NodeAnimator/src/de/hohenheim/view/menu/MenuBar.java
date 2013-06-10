package de.hohenheim.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.events.MenuBarEvents;

public class MenuBar {
    
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	
	public static Menu createMenu(Shell shell ){
		
		Menu menubar = new Menu(shell, SWT.BAR);
		
		MenuItem fileMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    fileMenuHeader.setText("&Datei");
	    fileMenuHeader.setImage(new Image(null,"img/Folder.png"));
        
	    // Menu File
	    
	    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);
	    
	    MenuItem fileNewProjectItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileNewProjectItem.setText("&Neues Projekt\tStrg + N");
	    fileNewProjectItem.setImage(new Image(null,"img/New_Project.png"));
	    
	    MenuItem fileLoadItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileLoadItem.setText("&Projekt Öffnen ...\tStrg + O");
	    fileLoadItem.setImage(new Image(null,"img/Projekt_Oeffnen.png"));

	    MenuItem fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileSaveItem.setText("&Projekt Speichern ...\tStrg + S");
	    fileSaveItem.setImage(new Image(null,"img/Save.png"));

	    MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);	
	    fileExitItem.setText("&Beenden");
	    fileExitItem.setImage(new Image(null,"img/Aus.png"));
	    
	    // Menu Train
	    
	    MenuItem zugMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    zugMenuHeader.setText("&Züge");
	    zugMenuHeader.setImage(new Image(null,"img/Train.png"));
	    
	    Menu trainMenu = new Menu (shell, SWT.DROP_DOWN);
	    zugMenuHeader.setMenu(trainMenu);
	    
	    MenuItem addTrainItem = new MenuItem(trainMenu, SWT.PUSH);
	    addTrainItem.setText("&Zug hinzufügen");
	    addTrainItem.setImage(new Image(null,"img/add.png"));
	    
	    MenuItem editTrainItem = new MenuItem(trainMenu, SWT.PUSH);
	    editTrainItem.setText("&Zug bearbeiten");
	    editTrainItem.setImage(new Image(null,"img/Edit.png"));
	    
	    MenuItem removeTrainItem = new MenuItem(trainMenu, SWT.PUSH);
	    removeTrainItem.setText("&Zug löschen");
	    removeTrainItem.setImage(new Image(null,"img/Delete.png"));

	    MenuItem importTrainItem = new MenuItem(trainMenu, SWT.PUSH);	
	    importTrainItem.setText("&Zug importieren");
	    importTrainItem.setImage(new Image(null,"img/Import.png"));
	    
	    MenuItem exportTrainItem = new MenuItem(trainMenu, SWT.PUSH);	
	    exportTrainItem.setText("&Zug exportieren");
	    exportTrainItem.setImage(new Image(null,"img/export.png"));
	    
	    // Menu Timetable 
	    
	    MenuItem fahrplanMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    fahrplanMenuHeader.setText("&Fahrpläne");
	    fahrplanMenuHeader.setImage(new Image(null,"img/TimeTable.png"));
	    
	    Menu fahrplanMenu = new Menu (shell, SWT.DROP_DOWN);
	    fahrplanMenuHeader.setMenu(fahrplanMenu);
	    
	    MenuItem addTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);
	    addTimetableItem.setText("&Fahrplan hinzufügen");
	    addTimetableItem.setImage(new Image(null,"img/add.png"));
	    
	    MenuItem editTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);
	    editTimetableItem.setText("&Fahrplan bearbeiten");
	    editTimetableItem.setImage(new Image(null,"img/Edit.png"));

	    MenuItem removeTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);
	    removeTimetableItem.setText("&Fahrplan löschen");
	    removeTimetableItem.setImage(new Image(null,"img/Delete.png"));

	    MenuItem importTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);	
	    importTimetableItem.setText("&Fahrplan importieren");
	    importTimetableItem.setImage(new Image(null,"img/Import.png"));
	    
	    MenuItem exportTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);	
	    exportTimetableItem.setText("&Fahrplan exportieren");
	    exportTimetableItem.setImage(new Image(null,"img/export.png"));

	    // Menu look
	    
	    MenuItem lookMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    lookMenuHeader.setText("&Ansicht");
	    lookMenuHeader.setImage(new Image(null,"img/cube32.png"));
	    
	    Menu lookMenu = new Menu (shell, SWT.DROP_DOWN);
	    lookMenuHeader.setMenu(lookMenu);
	    
	    
	    MenuItem animationLookItem = new MenuItem(lookMenu, SWT.PUSH);
	    animationLookItem.setText("&Animation");
	    animationLookItem.setImage(new Image(null,"img/Animation.png"));
	    
	    MenuItem projektLookItem = new MenuItem(lookMenu, SWT.PUSH);
	    projektLookItem.setText("&Projekte");
	    projektLookItem.setImage(new Image(null,"img/Projekt.png"));

	    MenuItem trainLookItem = new MenuItem(lookMenu, SWT.PUSH);
	    trainLookItem.setText("&Züge");
	    trainLookItem.setImage(new Image(null,"img/Train.png"));

	    MenuItem timetableLookItem = new MenuItem(lookMenu, SWT.PUSH);	
	    timetableLookItem.setText("&Fahrpläne");
	    timetableLookItem.setImage(new Image(null,"img/TimeTable.png"));
	    
	    // Menu Help
	    
	    MenuItem helpMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    helpMenuHeader.setText("&Hilfe");
	    helpMenuHeader.setImage(new Image(null,"img/Inof32.png"));
	    
	    Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);

	    MenuItem helpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpItem.setText("&Hilfe");
	    helpItem.setImage(new Image(null,"img/Help.png"));
	    
	    MenuItem helpInfoItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpInfoItem.setText("&Über SoBros");
	    helpInfoItem.setImage(new Image(null,"img/AboutUs.png"));
	    
	    //SelectionListener for the diffent menuItems
	    
	    fileNewProjectItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                  MenuBarEvents.addProject();
                
            }
        });
	    
	    fileLoadItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.open();
                
            }
        });
	    
	    fileSaveItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.save(true);
                
            }
        });
	    
	    fileExitItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                MenuBarEvents.closeProgramm();    
                
            }
        });
	    
	    addTrainItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                MenuBarEvents.addTrain();   
                
            }
        });
	    
	    editTrainItem.addSelectionListener(new SelectionAdapter() {
           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.editTrain(true);
                
            }
        });
	    
	    removeTrainItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.deletTrain();  
                
            }
        });
	    
	    importTrainItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.open();
	    		
            }
        });
	    
	    exportTrainItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.exportTrain();
                
            }
        });
	    
	    addTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.addTimeTable();
                
            }
        });
	    
	    editTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.edtitTimetable(true);
                
            }
        });
	    
	    removeTimetableItem.addSelectionListener(new SelectionAdapter() {
           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.deletTimetable();
                
            }
        });
	    
	    importTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.open();
                
            }
        });
	    
	    exportTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.save(true);
                
            }
        });
	    
	    animationLookItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		 MenuBarEvents.changeLookToAnimation();        
                
            }
        });
	    
	    projektLookItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                MenuBarEvents.changeLookToProject();    
                
            }
        });
	    
	    trainLookItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		 MenuBarEvents.changeLookToTrain();    
                
            }
        });
	    
	    timetableLookItem.addSelectionListener(new SelectionAdapter() {
           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		 MenuBarEvents.changeLookToTimetable(); 
                
            }
        });
	    
	    helpItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
               MenuBarEvents.showHelp();      
                
            }
        });
	    
	    helpInfoItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                
                
            }
        });
	    
		return menubar;
		
	}
    
	
	  
}
