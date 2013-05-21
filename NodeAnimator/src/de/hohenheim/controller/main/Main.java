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
					     TrainControllerCanvas.getGroupEditTrain().setVisible(false);
					     TrainControllerCanvas.getGroupDeletTrain().setVisible(false);	
					     TrainControllerCanvas.getGroupImportTrain().setVisible(false);
					     TrainControllerCanvas.getGroupExportTrain().setVisible(false);
					     
					     TimeTableControllerCanvas.getGroupControlSmall().setVisible(true);	
					     TimeTableControllerCanvas.getGroupAddTimeTable().setVisible(false);
					     TimeTableControllerCanvas.getGroupEditTimeTable().setVisible(false);
					     TimeTableControllerCanvas.getGroupDeletTimeTable().setVisible(false);	
					     TimeTableControllerCanvas.getGroupImportTimeTable().setVisible(false);
					     TimeTableControllerCanvas.getGroupExportTimeTable().setVisible(false);
					     big = false;
					}			
				}
			    else if(sizeshell.y >= 400){
			    	if(big == false){
					     TrainControllerCanvas.getGroupAddTrain().setVisible(true);
					     TrainControllerCanvas.getGroupEditTrain().setVisible(true);
					     TrainControllerCanvas.getGroupControlSmall().setVisible(false);
					     TrainControllerCanvas.getGroupDeletTrain().setVisible(true);	
					     TrainControllerCanvas.getGroupImportTrain().setVisible(true);
					     TrainControllerCanvas.getGroupExportTrain().setVisible(true);
					     
					     TimeTableControllerCanvas.getGroupControlSmall().setVisible(false);	
					     TimeTableControllerCanvas.getGroupAddTimeTable().setVisible(true);
					     TimeTableControllerCanvas.getGroupEditTimeTable().setVisible(true);
					     TimeTableControllerCanvas.getGroupDeletTimeTable().setVisible(true);	
					     TimeTableControllerCanvas.getGroupImportTimeTable().setVisible(true);
					     TimeTableControllerCanvas.getGroupExportTimeTable().setVisible(true);
					     big = true;
					}	
			    	
			    }
				TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
				
				try{
				    if (rowData[0] != null){
					     TrainControllerCanvas.getTextID2().setText(rowData[0].getText(0));
					     TrainControllerCanvas.getTextSpeed2().setText(rowData[0].getText(2));
					     TrainControllerCanvas.getTypOfTrain_combo2().setText(rowData[0].getText(1));
					}
				}
				catch(ArrayIndexOutOfBoundsException e){
					
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
