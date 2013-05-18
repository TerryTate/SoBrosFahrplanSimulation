package de.hohenheim.view.tab;



import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.hohenheim.view.composite.CompositeAnimation;
import de.hohenheim.view.composite.CompositeTrain;



public class TabFolder extends CTabFolder{
	
	private static final String[] tabNamesDE = {"Animation","Projekte","Züge","Fahrpläne"};
	private static CompositeTrain compositeTrain;
	
	public TabFolder(Composite parent, int style, Display display) {
		super(parent, style);
		
		// Animation Tab 
	    CTabItem cTabAnimationItem = new CTabItem(this, SWT.NULL );
	    cTabAnimationItem.setText(tabNamesDE[0]);	
	    CompositeAnimation compositeAnimation = new CompositeAnimation(this, SWT.NULL);
	    cTabAnimationItem.setControl(compositeAnimation);
	    
        // Project Tab	
	    CTabItem cTabProjectItem = new CTabItem(this, SWT.NULL);
	    cTabProjectItem.setText(tabNamesDE[1]);
	        
        // Train Tab
	    CTabItem cTabTrainItem = new CTabItem(this, SWT.NULL);
	    cTabTrainItem.setText(tabNamesDE[2]);
	    compositeTrain = new CompositeTrain(this, SWT.NULL);
	    cTabTrainItem.setControl(compositeTrain);
	        
        // TimeTable Tab
	    CTabItem cTabTimeTableItem = new CTabItem(this, SWT.NULL);
	    cTabTimeTableItem.setText(tabNamesDE[3]);
	     
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
