package de.hohenheim.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
        
	    // Menu File
	    
	    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);
	    
	    MenuItem fileNewProjectItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileNewProjectItem.setText("&Neues Projekt\tStrg + N");
	    
	    MenuItem fileLoadItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileLoadItem.setText("&Projekt Öffnen ...\tStrg + O");

	    MenuItem fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileSaveItem.setText("&Projekt Speichern ...\tStrg + S");

	    MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);	
	    fileExitItem.setText("&Beenden");
	    
	    // Menu Train
	    
	    MenuItem zugMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    zugMenuHeader.setText("&Züge");
	    
	    Menu trainMenu = new Menu (shell, SWT.DROP_DOWN);
	    zugMenuHeader.setMenu(trainMenu);
	    
	    MenuItem addTrainItem = new MenuItem(trainMenu, SWT.PUSH);
	    addTrainItem.setText("&Zug hinzufügen");
	    
	    MenuItem editTrainItem = new MenuItem(trainMenu, SWT.PUSH);
	    editTrainItem.setText("&Zug bearbeiten");

	    MenuItem removeTrainItem = new MenuItem(trainMenu, SWT.PUSH);
	    removeTrainItem.setText("&Zug löschen");

	    MenuItem importTrainItem = new MenuItem(trainMenu, SWT.PUSH);	
	    importTrainItem.setText("&Zug importieren");
	    
	    MenuItem exportTrainItem = new MenuItem(trainMenu, SWT.PUSH);	
	    exportTrainItem.setText("&Zug exportieren");
	    
	    // Menu Timetable 
	    
	    MenuItem fahrplanMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    fahrplanMenuHeader.setText("&Fahrpläne");
	    
	    Menu fahrplanMenu = new Menu (shell, SWT.DROP_DOWN);
	    fahrplanMenuHeader.setMenu(fahrplanMenu);
	    
	    MenuItem addTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);
	    addTimetableItem.setText("&Fahrplan hinzufügen");
	    
	    MenuItem editTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);
	    editTimetableItem.setText("&Fahrplan bearbeiten");

	    MenuItem removeTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);
	    removeTimetableItem.setText("&Fahrplan löschen");

	    MenuItem importTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);	
	    importTimetableItem.setText("&Fahrplan importieren");
	    
	    MenuItem exportTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);	
	    exportTimetableItem.setText("&Fahrplan exportieren");

	    // Menu look
	    
	    MenuItem lookMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    lookMenuHeader.setText("&Ansicht");
	    
	    Menu lookMenu = new Menu (shell, SWT.DROP_DOWN);
	    lookMenuHeader.setMenu(lookMenu);
	    
	    
	    MenuItem animationLookItem = new MenuItem(lookMenu, SWT.PUSH);
	    animationLookItem.setText("&Animation");
	    
	    MenuItem projektLookItem = new MenuItem(lookMenu, SWT.PUSH);
	    projektLookItem.setText("&Projekte");

	    MenuItem trainLookItem = new MenuItem(lookMenu, SWT.PUSH);
	    trainLookItem.setText("&Züge");

	    MenuItem timetableLookItem = new MenuItem(lookMenu, SWT.PUSH);	
	    timetableLookItem.setText("&Fahrpläne");
	    
	    // Menu Help
	    
	    MenuItem helpMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    helpMenuHeader.setText("&Hilfe");

	    Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);

	    MenuItem helpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpItem.setText("&Hilfe");
	    
	    MenuItem helpInfoItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpInfoItem.setText("&Über SoBros");
	    
	    //SelectionListener for the diffent menuItems
	    
	    fileNewProjectItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                  
                
            }
        });
	    
	    fileLoadItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                
                
            }
        });
	    
	    fileSaveItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                   
                
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
	    		
	    		MenuBarEvents.openTrain();
	    		
            }
        });
	    
	    exportTrainItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.saveTrain();
                
            }
        });
	    
	    addTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.addTimeTable();
                
            }
        });
	    
	    editTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.edtitTimetable();
                
            }
        });
	    
	    removeTimetableItem.addSelectionListener(new SelectionAdapter() {
           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		MenuBarEvents.deletTimetable();
                
            }
        });
	    
	    importTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                  
                
            }
        });
	    
	    exportTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                
                
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
