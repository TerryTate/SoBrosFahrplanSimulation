package de.hohenheim.view.composite;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.controller.main.Main;
import de.hohenheim.controller.main.TrainData;

public class CompositeTrain extends Composite{
	
	/*
	 * 
	 * 
	 */
	
	private ScrolledComposite scrollComposite;
	private GridLayout gridLayout;
	private Table trainTable;

	/*
	 * 
	 * 
	 */
	
	public CompositeTrain(Composite parent, int style) {
	
		super(parent, style);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);
		this.setBackground(ColorConstants.black);
		
		scrollComposite = new ScrolledComposite(this, SWT.H_SCROLL  | SWT.V_SCROLL);
		
		scrollComposite.setVisible(true);
		scrollComposite.setExpandHorizontal(true);
	    scrollComposite.setExpandVertical(true);
		scrollComposite.setBackground(ColorConstants.blue);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		scrollComposite.setLayoutData(gridData);
		
		
		trainTable = new Table(scrollComposite, SWT.FULL_SELECTION);
	    TableColumn idTrain = new TableColumn(getTrainTable(),SWT.CENTER);
	    TableColumn tc2 = new TableColumn(getTrainTable(), SWT.CENTER);
	    TableColumn tc3 = new TableColumn(getTrainTable(), SWT.CENTER);
	    idTrain.setText("ID");
	    tc2.setText("Last Name");
	    tc3.setText("Address");
	    idTrain.setWidth(70);
	    tc2.setWidth(70);
	    tc3.setWidth(80);
	    getTrainTable().setHeaderVisible(true);
	    
	    scrollComposite.setContent(getTrainTable());
		
		TrainControllerCanvas canvasControl = new TrainControllerCanvas(this, SWT.BORDER);
			
		canvasControl.setBackground(ColorConstants.white);
    	canvasControl.setBounds(0, 0, 800, 0);
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasControl.setLayoutData(gridData);
	}
	
	/*
	 * 
	 * 
	 */
	
	public Table getTrainTable(){
		return this.trainTable;
	}
	
	public void loadTableEntry(){
		
		getTrainTable().removeAll();
		    
		for(int i = 0; i < Main.trainList.size(); i++){
		    TrainData trainData = Main.trainList.get(i);
			TableItem tableItem = new TableItem(getTrainTable(), SWT.LEFT);
			tableItem.setText(new String[]{String.valueOf(trainData.getID()),
					String.valueOf(trainData.getSpeed()),
					trainData.getStartStation()});
			
		}
		
	}
}
