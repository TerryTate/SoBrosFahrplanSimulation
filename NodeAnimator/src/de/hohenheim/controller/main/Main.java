package de.hohenheim.controller.main;
	
import java.util.ArrayList;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.canvas.TimeTableControllerCanvas;
import de.hohenheim.view.canvas.TrainControllerCanvas;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.menu.MenuBar;
import de.hohenheim.view.tab.TabFolder;

public class Main {
	
	private static Composite tabComposite;
	private static Composite bottomComposite;
	private static TabFolder tabFolder;
	public static ArrayList<TrainData> trainListAll = new ArrayList();
	public static ArrayList<Timetable> timetableListAll = new ArrayList();
	public static boolean big = true;
	private static boolean fill = false;
	private static int oldValue = -1;
	private static Shell shell;
	/**
	 * @param args
	 */
	
	/*
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		
		Display display = Display.getDefault();
       
		setShell(new Shell());
		getShell().setText("Train animation example");
		getShell().setImage(new Image(null,"img/forklift-truck-logo.png"));
		getShell().setSize(820, 720);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
	    getShell().setLayout(gridLayout);
		
		getShell().setMenuBar(MenuBar.createMenu(getShell()));
		
		tabComposite = new Composite(getShell(), SWT.BORDER );
		tabComposite.setBackground(ColorConstants.lightGray);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		tabComposite.setLayoutData(gridData);
		tabComposite.setLayout(gridLayout);
		
		
		bottomComposite = new Composite(getShell(), SWT.BORDER );
		bottomComposite.setBackground(ColorConstants.lightGray);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 20;
    	bottomComposite.setLayoutData(gridData);
    	
	    tabFolder = new TabFolder(tabComposite, SWT.BORDER, display);   

	    getShell().open();
    
	
		while (!getShell().isDisposed()) {			
			if (!display.readAndDispatch()) {
	    
			    display.sleep();
		   }
	
		}
	
	}

	public static TabFolder getcTabFolder() {
		return tabFolder;
	}

	public static void setTabFolder(TabFolder tabFolder) {
		Main.tabFolder = tabFolder;
	}

	public static Shell getShell() {
		return shell;
	}

	public static void setShell(Shell shell) {
		Main.shell = shell;
	}

}
