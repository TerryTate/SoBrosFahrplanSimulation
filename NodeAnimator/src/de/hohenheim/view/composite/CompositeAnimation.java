package de.hohenheim.view.composite;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.map.Map;
import de.hohenheim.view.map.NodeMap;

public class CompositeAnimation extends Composite{
    
	private NodeMap map;
   
	/*
	 * 
	 * 
	 */

	public CompositeAnimation(Composite parent, int style) {
		    super(parent, style);
		    GridLayout gridLayout = new GridLayout();
		    gridLayout.numColumns = 2;
		    this.setLayout(gridLayout);
		    
		    
		    this.setBackground(ColorConstants.black);	
			
			Canvas canvasMap = new Canvas(this, SWT.BORDER);
			canvasMap.setBackground(ColorConstants.white);
			
			GridData gridData = new GridData();
			gridData.horizontalAlignment = SWT.FILL;
			gridData.grabExcessHorizontalSpace = true;
			gridData.verticalAlignment = GridData.FILL;
			gridData.grabExcessVerticalSpace = true;
			canvasMap.setLayoutData(gridData);
			map = Map.createMap(canvasMap, 1);
			
			
	        AnimationControllerCanvas canvasControl = new AnimationControllerCanvas(this, SWT.BORDER, map);
			canvasControl.setBackground(ColorConstants.white);
			canvasControl.setBounds(0, 0, 800, 0);
			gridData = new GridData();
			gridData.verticalAlignment = GridData.FILL;
			gridData.grabExcessVerticalSpace = true;
			canvasControl.setLayoutData(gridData);
			
		
	}

}
