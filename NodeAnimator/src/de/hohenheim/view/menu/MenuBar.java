package de.hohenheim.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.events.CentralEventController;
import de.hohenheim.view.mobile.ImageHelper;

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
	    fileNewProjectItem.setImage(ImageHelper.newProjekt);
	    
	    MenuItem fileLoadItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileLoadItem.setText("&Projekt �ffnen ...\tStrg + O");
	    fileLoadItem.setImage(ImageHelper.openFolder);;

	    MenuItem fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileSaveItem.setText("&Projekt Speichern ...\tStrg + S");
	    fileSaveItem.setImage(ImageHelper.save);

	    MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);	
	    fileExitItem.setText("&Beenden");
	    fileExitItem.setImage(ImageHelper.aus);
	    
	    // Menu Train
	    
	    MenuItem zugMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    zugMenuHeader.setText("&Z�ge");
	    zugMenuHeader.setImage(ImageHelper.train);
	    
	    Menu trainMenu = new Menu (shell, SWT.DROP_DOWN);
	    zugMenuHeader.setMenu(trainMenu);
	    
	    MenuItem addTrainItem = new MenuItem(trainMenu, SWT.PUSH);
	    addTrainItem.setText("&Zug hinzuf�gen\tStrg + Z");
	    addTrainItem.setImage(ImageHelper.add);
	    
	    MenuItem editTrainItem = new MenuItem(trainMenu, SWT.PUSH);
	    editTrainItem.setText("&Zug bearbeiten\tStrg + E");
	    editTrainItem.setImage(ImageHelper.editF);
	    
	    MenuItem removeTrainItem = new MenuItem(trainMenu, SWT.PUSH);
	    removeTrainItem.setText("&Zug l�schen\tStrg + D");
	    removeTrainItem.setImage(ImageHelper.delete);

	    MenuItem importTrainItem = new MenuItem(trainMenu, SWT.PUSH);	
	    importTrainItem.setText("&Zug importieren\tStrg + I");
	    importTrainItem.setImage(ImageHelper.importPic);
	    
	    MenuItem exportTrainItem = new MenuItem(trainMenu, SWT.PUSH);	
	    exportTrainItem.setText("&Zug exportieren\tStrg + X");
	    exportTrainItem.setImage(ImageHelper.export);
	    
	    // Menu Timetable 
	    
	    MenuItem fahrplanMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    fahrplanMenuHeader.setText("&Fahrpl�ne");
	    fahrplanMenuHeader.setImage(ImageHelper.timeTable);
	    
	    Menu fahrplanMenu = new Menu (shell, SWT.DROP_DOWN);
	    fahrplanMenuHeader.setMenu(fahrplanMenu);
	    
	    MenuItem addTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);
	    addTimetableItem.setText("&Fahrplan hinzuf�gen\tStrg + F");
	    addTimetableItem.setImage(ImageHelper.add);
	    
	    MenuItem editTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);
	    editTimetableItem.setText("&Fahrplan bearbeiten\tStrg + Alt + E");
	    editTimetableItem.setImage(ImageHelper.editF);;

	    MenuItem removeTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);
	    removeTimetableItem.setText("&Fahrplan l�schen\tStrg + Alt + D");
	    removeTimetableItem.setImage(ImageHelper.delete);

	    MenuItem importTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);	
	    importTimetableItem.setText("&Fahrplan importieren\tStrg + Alt + I");
	    importTimetableItem.setImage(ImageHelper.importPic);
	    
	    MenuItem exportTimetableItem = new MenuItem(fahrplanMenu, SWT.PUSH);	
	    exportTimetableItem.setText("&Fahrplan exportieren\tStrg + Alt + X");
	    exportTimetableItem.setImage(ImageHelper.export);

	    // Menu look
	    
	    MenuItem lookMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    lookMenuHeader.setText("&Ansicht");
	    lookMenuHeader.setImage(new Image(null,"img/cube32.png"));
	    
	    Menu lookMenu = new Menu (shell, SWT.DROP_DOWN);
	    lookMenuHeader.setMenu(lookMenu);
	    
	    
	    MenuItem animationLookItem = new MenuItem(lookMenu, SWT.PUSH);
	    animationLookItem.setText("&Animation\tStrg + A");
	    animationLookItem.setImage(ImageHelper.animation);
	    
	    MenuItem projektLookItem = new MenuItem(lookMenu, SWT.PUSH);
	    projektLookItem.setText("&Projekte\tStrg + P");
	    projektLookItem.setImage(ImageHelper.project);

	    MenuItem trainLookItem = new MenuItem(lookMenu, SWT.PUSH);
	    trainLookItem.setText("&Z�ge\tStrg + T");
	    trainLookItem.setImage(ImageHelper.train);

	    MenuItem timetableLookItem = new MenuItem(lookMenu, SWT.PUSH);	
	    timetableLookItem.setText("&Fahrpl�ne\tStrg + Alt + T");
	    timetableLookItem.setImage(ImageHelper.timeTable);
	    
	    // Menu Help
	    
	    MenuItem helpMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    helpMenuHeader.setText("&Hilfe");
	    helpMenuHeader.setImage(ImageHelper.info);
	    
	    Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);

	    MenuItem helpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpItem.setText("&Hilfe\tStrg + H");
	    helpItem.setImage(ImageHelper.help);
	    
	    MenuItem helpInfoItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpInfoItem.setText("&�ber SoBros\tStrg + U");
	    helpInfoItem.setImage(ImageHelper.aboutUs);
	    
	    //SelectionListener for the diffent menuItems
	    
	    fileNewProjectItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                  CentralEventController.openAddDialog(2);
                
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
	    		
                CentralEventController.closeProgramm();    
                
            }
        });
	    
	    addTrainItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                CentralEventController.openAddDialog(0);   
                
            }
        });
	    
	    editTrainItem.addSelectionListener(new SelectionAdapter() {
           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		CentralEventController.openEditDialog(true, 0);
                
            }
        });
	    
	    removeTrainItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		CentralEventController.openDeleteDialog(true, 0);  
                
            }
        });
	    
	    importTrainItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		CentralEventController.openTrain();
	    		
            }
        });
	    
	    exportTrainItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		CentralEventController.openExportDialog(0);
                
            }
        });
	    
	    addTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		CentralEventController.openAddDialog(1);
                
            }
        });
	    
	    editTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		CentralEventController.openEditDialog(true, 1);
                
            }
        });
	    
	    removeTimetableItem.addSelectionListener(new SelectionAdapter() {
           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		CentralEventController.openDeleteDialog(true, 1);
                
            }
        });
	    
	    importTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    	//	MenuBarEvents.open();
                
            }
        });
	    
	    exportTimetableItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		CentralEventController.openExportDialog(1);
                
            }
        });
	    
	    animationLookItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		 CentralEventController.changeLook(0);        
                
            }
        });
	    
	    projektLookItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
                CentralEventController.changeLook(1);    
                
            }
        });
	    
	    trainLookItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		 CentralEventController.changeLook(2);    
                
            }
        });
	    
	    timetableLookItem.addSelectionListener(new SelectionAdapter() {
           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		 CentralEventController.changeLook(3); 
                
            }
        });
	    
	    helpItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
               CentralEventController.showHelp();      
                
            }
        });
	    
	    helpInfoItem.addSelectionListener(new SelectionAdapter() {
	           
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		CentralEventController.showInfo();
                
            }
        });
	    
		return menubar;
		
	}
    
	
	  
}
