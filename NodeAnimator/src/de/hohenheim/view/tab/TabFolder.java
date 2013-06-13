package de.hohenheim.view.tab;



import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.hohenheim.view.composite.CompositeAnimation;
import de.hohenheim.view.composite.CompositeProject;
import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.mobile.ImageHelper;



public class TabFolder extends CTabFolder{
	
	private static final String[] tabNamesDE = {"Animation","Projekte","Züge","Fahrpläne"};
	private static CompositeTrain compositeTrain;
	private CompositeTimeTable compositeTimeTable;
	private CompositeProject compositeProject;
	
	public TabFolder(Composite parent, int style, Display display) {
		super(parent, style);
		
		// Animation Tab 
	    CTabItem cTabAnimationItem = new CTabItem(this, SWT.NULL );
	    cTabAnimationItem.setText(tabNamesDE[0]);	
	    cTabAnimationItem.setImage(ImageHelper.animation);
	    CompositeAnimation compositeAnimation = new CompositeAnimation(this, SWT.NULL);
	    cTabAnimationItem.setControl(compositeAnimation);
	    
        // Project Tab	
	    CTabItem cTabProjectItem = new CTabItem(this, SWT.NULL);
	    cTabProjectItem.setText(tabNamesDE[1]);
	    cTabProjectItem.setImage(ImageHelper.project);
	    compositeProject = new CompositeProject(this, SWT.NULL);
	    cTabProjectItem.setControl(compositeProject);
	        
        // Train Tab
	    CTabItem cTabTrainItem = new CTabItem(this, SWT.NULL);
	    cTabTrainItem.setText(tabNamesDE[2]);
	    cTabTrainItem.setImage(ImageHelper.train);
	    compositeTrain = new CompositeTrain(this, SWT.NULL);
	    cTabTrainItem.setControl(compositeTrain);
	        
        // TimeTable Tab
	    CTabItem cTabTimeTableItem = new CTabItem(this, SWT.NULL);
	    cTabTimeTableItem.setText(tabNamesDE[3]);
	    cTabTimeTableItem.setImage(ImageHelper.timeTable);
	    compositeTimeTable = new CompositeTimeTable(this, SWT.NULL);
	    cTabTimeTableItem.setControl(compositeTimeTable);
	     
	    //cTabFolder.setSimple(false);  Andere Form ist aber dann unterstrichen
	    
	    //Color Tab
 
	    this.setBackground(new Color[]{display.getSystemColor(SWT.COLOR_WHITE), display.getSystemColor(SWT.COLOR_GRAY)}, new int[]{100}, true);
	    this.setSelectionBackground(new Color[]{display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT), display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND)}, new int[]{100}, true);
	  
	    GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		this.setLayoutData(gridData);
		
	}
    
	
}
