package de.hohenheim.view.composite;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import de.hohenheim.view.map.Map;

public class CompositeAnimation {
	
	public static Composite createAnimationComposite (CTabFolder cTabFolder){
	
		Point size = (cTabFolder.getSize());
		
		Composite nodeComposite = new Composite(cTabFolder, SWT.BORDER);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		nodeComposite.setLayout(gridLayout);
		
		nodeComposite.setBackground(ColorConstants.black);
		
			
		Canvas canvasMap = new Canvas(nodeComposite, SWT.BORDER);
		
		canvasMap.setBackground(ColorConstants.white);
		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasMap.setLayoutData(gridData);
		Map.createMap(canvasMap);
		
        Canvas canvasControl = new Canvas(nodeComposite, SWT.BORDER);
		
		canvasControl.setBackground(ColorConstants.white);
		canvasControl.setBounds(0, 0, 800, 0);
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasControl.setLayoutData(gridData);
		
		
		
		
		return nodeComposite;
		
	}

}
