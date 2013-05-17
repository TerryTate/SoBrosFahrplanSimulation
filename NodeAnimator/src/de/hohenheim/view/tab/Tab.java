package de.hohenheim.view.tab;



import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.hohenheim.view.composite.CompositeAnimation;
import de.hohenheim.view.composite.CompositeTrain;



public class Tab {
	
	private static final String[] tabNamesDE = {"Animation","Projekte","Züge","Fahrpläne"};
	
	/*
	 *
	 * 
	 */
	
	public static CTabFolder createTabFolder(Composite tabComposite, Display display){
		
		final CTabFolder cTabFolder = new CTabFolder(tabComposite, SWT.BORDER_SOLID);
	    
		
		
		// Animation Tab 
	    CTabItem cTabAnimationItem = new CTabItem(cTabFolder, SWT.NULL );
	    cTabAnimationItem.setText(tabNamesDE[0]);	
	    CompositeAnimation compositeAnimation = new CompositeAnimation(cTabFolder, SWT.NULL);
	    cTabAnimationItem.setControl(compositeAnimation);
	    
        // Project Tab	
	    CTabItem cTabProjectItem = new CTabItem(cTabFolder, SWT.NULL);
	    cTabProjectItem.setText(tabNamesDE[1]);
	        
        // Train Tab
	    CTabItem cTabTrainItem = new CTabItem(cTabFolder, SWT.NULL);
	    cTabTrainItem.setText(tabNamesDE[2]);
	    CompositeTrain compositeTrain = new CompositeTrain(cTabFolder, SWT.NULL);
	    cTabTrainItem.setControl(compositeTrain);
	        
        // TimeTable Tab
	    CTabItem cTabTimeTableItem = new CTabItem(cTabFolder, SWT.NULL);
	    cTabTimeTableItem.setText(tabNamesDE[3]);
	     
	    //cTabFolder.setSimple(false);  Andere Form ist aber dann unterstrichen
	    
	    //Color Tab
 
	    cTabFolder.setBackground(new Color[]{display.getSystemColor(SWT.COLOR_WHITE), display.getSystemColor(SWT.COLOR_GRAY)}, new int[]{100}, true);
	    cTabFolder.setSelectionBackground(new Color[]{display.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT), display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND)}, new int[]{100}, true);
	    
	    
		return cTabFolder;
	}

}
