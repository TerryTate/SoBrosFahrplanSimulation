package de.hohenheim.controller.main;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.ControllerCanvas;
import de.hohenheim.view.map.Map;


public class Tab {
	
	private static final String[] tabNamesDE = {"Animation","Projekte","Züge","Fahrpläne"};
	
	public static CTabFolder createTabFolder(Composite tabComposite){
		
		final CTabFolder cTabFolder = new CTabFolder(tabComposite, SWT.NONE);

	    for (int loopIndex = 0; loopIndex < 4; loopIndex++) {
	      
	        CTabItem cTabItem = new CTabItem(cTabFolder, SWT.NULL);
	        cTabItem.setText(tabNamesDE[loopIndex]);
	       
	        Composite nodeComposite = new Composite(cTabFolder, SWT.BORDER);
			nodeComposite.setLayout(new FillLayout());
			nodeComposite.setBackground(ColorConstants.black);
			nodeComposite.setBounds(10,10,600,500);
				
			Canvas c = new Canvas(cTabFolder, SWT.FILL);
			
			c.setBackground(ColorConstants.white);
			c.setBounds(0,0,600,500);
			Map.createMap(c);
			
			cTabItem.setControl(nodeComposite);
			cTabItem.setControl(c);


		    
	       
			

	    }
	    cTabFolder.setSimple(false);
	    
	    
	  
		return cTabFolder;
	}

}
