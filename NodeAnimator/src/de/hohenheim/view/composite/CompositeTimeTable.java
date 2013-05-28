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


public class CompositeTimeTable extends Composite {
    
	private ScrolledComposite scrollComposite;
	private GridLayout gridLayout;
	private static Table timeTableTable;
	
	public CompositeTimeTable(Composite parent, int style) {
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
		
        setTimeTableTable(new Table(scrollComposite, SWT.FULL_SELECTION));
		
	    TableColumn idTimeTable = new TableColumn(getTimeTableTable(),SWT.CENTER);
	    TableColumn timeTableName = new TableColumn(getTimeTableTable(), SWT.CENTER);
	    TableColumn drivingDays = new TableColumn(getTimeTableTable(), SWT.CENTER);
	    TableColumn startStation = new TableColumn(getTimeTableTable(),SWT.CENTER);
	    TableColumn endStation = new TableColumn(getTimeTableTable(), SWT.CENTER);
	    TableColumn middleStation = new TableColumn(getTimeTableTable(), SWT.CENTER);
	    
	    idTimeTable.setText("ID");
	    timeTableName.setText("Fahrplanname");
	    drivingDays.setText("Fahrtage");
	    startStation.setText("Startstation");
	    endStation.setText("Endstation");
	    middleStation.setText("Zwischenstation");
	    
	    idTimeTable.setWidth(70);
	    timeTableName.setWidth(70);
	    drivingDays.setWidth(80);
	    startStation.setWidth(80);
	    endStation.setWidth(80);
	    middleStation.setWidth(80);
	    
	    getTimeTableTable().setHeaderVisible(true);
	    
	    scrollComposite.setContent(getTimeTableTable());
		
		TimeTableControllerCanvas canvasControl = new TimeTableControllerCanvas(this, SWT.BORDER );	
		canvasControl.setBackground(ColorConstants.white);
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasControl.setLayoutData(gridData);
	}

	public static Table getTimeTableTable() {
		return timeTableTable;
	}

	public static void setTimeTableTable(Table timeTableTable) {
		CompositeTimeTable.timeTableTable = timeTableTable;
	}

}
