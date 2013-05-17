package de.hohenheim.controller.main;
	
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import de.hohenheim.view.menu.MenuBar;
import de.hohenheim.view.tab.Tab;

public class Main {

	private static Composite tabComposite;
	private static Composite bottomComposite;
	public static CTabFolder cTabFolder;
	/**
	 * @param args
	 */
	
	Point size;
	
	/*
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setText("Train animation example");
		shell.setImage(new Image(null,"img/forklift-truck-logo.png"));
		shell.setSize(820, 720);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
	    shell.setLayout(gridLayout);
		
		shell.setMenuBar(MenuBar.createMenu(shell));
		
		tabComposite = new Composite(shell, SWT.BORDER );
		tabComposite.setBackground(ColorConstants.lightGray);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		tabComposite.setLayoutData(gridData);
		tabComposite.setLayout(gridLayout);
		
		//Composite muss noch bischen schmaler werden 
		bottomComposite = new Composite(shell, SWT.BORDER );
		bottomComposite.setBackground(ColorConstants.lightGray);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 20;
    	bottomComposite.setLayoutData(gridData);
    	


		
	    cTabFolder = Tab.createTabFolder(tabComposite, display);   
	    gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		cTabFolder.setLayoutData(gridData);
	   
	    
	   
	    
	    shell.open();
    
	
		while (!shell.isDisposed()) {			
			if (!display.readAndDispatch()) {
			    
			    display.sleep();
		   }
		 }
	
	}

	

}
