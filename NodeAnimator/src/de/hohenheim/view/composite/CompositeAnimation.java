package de.hohenheim.view.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.map.Map;
import de.hohenheim.view.map.NodeMap;

/**
 * This class create a new CompositeAnimation with a Table and call the
 * Constructor for a new AnimationControllerCanvas
 * 
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class CompositeAnimation extends Composite {

	/**
	 * A Constructor for a new Composite.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public CompositeAnimation(Composite parent, int style) {
		super(parent, style);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);

		Canvas canvasMap = new Canvas(this, SWT.BORDER);

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasMap.setLayoutData(gridData);
		NodeMap map = Map.createMap(canvasMap, 1);

		AnimationControllerCanvas canvasControl = new AnimationControllerCanvas(
				this, SWT.BORDER, map);

		canvasControl.setBounds(0, 0, 800, 0);
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasControl.setLayoutData(gridData);

	}

}
