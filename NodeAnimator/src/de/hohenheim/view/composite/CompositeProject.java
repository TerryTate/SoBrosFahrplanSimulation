package de.hohenheim.view.composite;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.hohenheim.view.canvas.TimeTableControllerCanvas;

public class CompositeProject extends Composite {
	
	  
	private ScrolledComposite scrollComposite;
	private GridLayout gridLayout;
	private static Table projectTable;

	public CompositeProject(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	private void createContent() {
		
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
		
		projectTable = new Table(scrollComposite, SWT.FULL_SELECTION);
	    TableColumn idProject = new TableColumn(projectTable,SWT.CENTER);
	    TableColumn projectName = new TableColumn(projectTable, SWT.CENTER);
	    TableColumn idtrains = new TableColumn(projectTable, SWT.CENTER);
	    TableColumn idtimeTable = new TableColumn(projectTable, SWT.CENTER);
	    idProject.setText("ID");
	    projectName.setText("Projekt Name");
	    idtrains.setText("ID Züge");
	    idtimeTable.setText("ID Fahrplan");
	    idProject.setWidth(70);
	    projectName.setWidth(70);
	    idtrains.setWidth(80);
	    idtimeTable.setWidth(80);
	    projectTable.setHeaderVisible(true);
	    projectTable.setLinesVisible(true);
	    
	    scrollComposite.setContent(projectTable);
		
		TimeTableControllerCanvas canvasControl = new TimeTableControllerCanvas(this, SWT.BORDER );	
		canvasControl.setBackground(ColorConstants.white);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasControl.setLayoutData(gridData);
		
	}

}
