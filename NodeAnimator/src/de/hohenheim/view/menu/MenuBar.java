package de.hohenheim.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

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
        
	    //
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
	    
	    //
	    MenuItem zugMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    zugMenuHeader.setText("&Züge");
	    
	    Menu zugMenu = new Menu (shell, SWT.DROP_DOWN);
	    zugMenuHeader.setMenu(zugMenu);
	    
	    //
	    MenuItem fahrplanMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    fahrplanMenuHeader.setText("&Fahrpläne");
	    
	    Menu fahrplanMenu = new Menu (shell, SWT.DROP_DOWN);
	    fahrplanMenuHeader.setMenu(fahrplanMenu);

	    //
	    MenuItem lookMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    lookMenuHeader.setText("&Ansicht");
	    
	    Menu lookMenu = new Menu (shell, SWT.DROP_DOWN);
	    lookMenuHeader.setMenu(lookMenu);
	    
	    //
	    MenuItem helpMenuHeader = new MenuItem(menubar, SWT.CASCADE);
	    helpMenuHeader.setText("&Hilfe");

	    Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);

	    MenuItem helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("&Hilfe");
	    
	    MenuItem helpInfoItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpInfoItem.setText("&Über SoBros");
	    
	    
	    

//	    fileExitItem.addSelectionListener(new fileExitItemListener());
//	    fileSaveItem.addSelectionListener(new fileSaveItemListener());
//	    helpGetHelpItem.addSelectionListener(new helpGetHelpItemListener());
	    
		return menubar;
		
	}

}
