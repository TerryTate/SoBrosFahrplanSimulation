package de.hohenheim.view.composite;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import de.hohenheim.view.canvas.TrainControllerCanvas;

public class CompositeTrain extends Composite{
	
	/*
	 * 
	 * 
	 */
	
	private ScrolledComposite scrollComposite;
	private GridLayout gridLayout;
	private static Table trainTable;

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
	    TableColumn typOfTrain = new TableColumn(getTrainTable(), SWT.CENTER);
	    TableColumn maxSpeed = new TableColumn(getTrainTable(), SWT.CENTER);
	    idTrain.setText("ID");
	    typOfTrain.setText("ZugTyp");
	    maxSpeed.setText("Höchstgeschwindigkeit");
	    idTrain.setWidth(70);
	    typOfTrain.setWidth(70);
	    maxSpeed.setWidth(80);
	    getTrainTable().setHeaderVisible(true);
	    
	    scrollComposite.setContent(getTrainTable());
		
		TrainControllerCanvas canvasControl = new TrainControllerCanvas(this, SWT.BORDER );	
		canvasControl.setBackground(ColorConstants.white);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasControl.setLayoutData(gridData);
		
		
	}
	
	/*
	 * 
	 * 
	 */
	
	public static Table getTrainTable(){
		return trainTable;
	}
	
}
