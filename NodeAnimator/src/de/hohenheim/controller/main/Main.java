package de.hohenheim.controller.main;
	
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.ControllerCanvas;
import de.hohenheim.view.map.Map;
import de.hohenheim.view.menu.MenuBar;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Shell shell = new Shell();
		shell.setText("Train animation example");
		shell.setImage(new Image(null,"img/forklift-truck-logo.png"));
		shell.setSize(800, 550);
		shell.setMenuBar(MenuBar.createMenu(shell));
		shell.open();

		Composite nodeComposite = new Composite(shell, SWT.BORDER);
		nodeComposite.setLayout(new FillLayout());
		nodeComposite.setBackground(ColorConstants.black);
		nodeComposite.setBounds(10,10,600,500);
		Canvas c = new Canvas(nodeComposite, SWT.FILL);
		
		c.setBackground(ColorConstants.white);
		c.setBounds(0,0,600,500);
	    
//	    new Train(map, map.getNodes().get("1"), 1);
//	    new Train(map, map.getNodes().get("2"), 2);

	    new ControllerCanvas(shell, SWT.FILL, Map.createMap(c));
	    
		Display display = Display.getDefault();
		while (!shell.isDisposed()) {			
			if (!display.readAndDispatch()) {    	  			   
			   display.sleep();
		   }
		 }
	
	}

	

}
