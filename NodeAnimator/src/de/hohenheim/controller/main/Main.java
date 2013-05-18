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
import de.hohenheim.view.composite.TrainControllerCanvas;
import de.hohenheim.view.menu.MenuBar;
import de.hohenheim.view.tab.TabFolder;

public class Main {

	private static Composite tabComposite;
	private static Composite bottomComposite;
	private static TabFolder tabFolder;
	public static ArrayList<TrainData> trainListAll = new ArrayList();
	public static boolean big = true;
	
	/**
	 * @param args
	 */
	
	/*
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setText("Train animation example");
		shell.setImage(new Image(null,"img/forklift-truck-logo.png"));
		shell.setSize(820, 720);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
	    shell.setLayout(gridLayout);
		
		shell.setMenuBar(MenuBar.createMenu(shell));
		
		tabComposite = new Composite(shell, SWT.BORDER );
		tabComposite.setBackground(ColorConstants.lightGray);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		tabComposite.setLayoutData(gridData);
		tabComposite.setLayout(gridLayout);
		
		
		bottomComposite = new Composite(shell, SWT.BORDER );
		bottomComposite.setBackground(ColorConstants.lightGray);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 20;
    	bottomComposite.setLayoutData(gridData);
    	
	    tabFolder = new TabFolder(tabComposite, SWT.BORDER, display);   

	    shell.open();
    
	
		while (!shell.isDisposed()) {			
			if (!display.readAndDispatch()) {
				Point sizeshell = shell.getSize();
				
				if (sizeshell.y < 300){
					if(big == true){
					     TrainControllerCanvas.getGroupControlSmall().setVisible(true);	
					     TrainControllerCanvas.getGroupAddTrain().setVisible(false);
					     big = false;
					}			
				}
			    else if(sizeshell.y >= 300){
			    	if(big == false){
					     TrainControllerCanvas.getGroupAddTrain().setVisible(true);
					     TrainControllerCanvas.getGroupControlSmall().setVisible(false);
					     big = true;
					}	
			    	
			    }
			    		
			    
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

}