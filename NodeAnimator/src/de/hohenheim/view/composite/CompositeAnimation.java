package de.hohenheim.view.composite;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import de.hohenheim.view.map.Map;

public class CompositeAnimation {
	
	public static Composite createAnimationComposite (CTabFolder cTabFolder){
		
		Composite nodeComposite = new Composite(cTabFolder, SWT.BORDER);
		nodeComposite.setLayout(new FillLayout());
		nodeComposite.setBackground(ColorConstants.black);
		nodeComposite.setBounds(10,10,600,500);
			
		Canvas c = new Canvas(nodeComposite, SWT.FILL);
		
		c.setBackground(ColorConstants.white);
		c.setBounds(0,0,600,500);
		Map.createMap(c);
		
		
		return nodeComposite;
		
	}

}
