package de.hohenheim.controller.main;
	
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import de.hohenheim.view.menu.MenuBar;
import de.hohenheim.view.tab.Tab;

public class Main {

	/**
	 * @param args
	 */
	
	Point size;
	
	/*
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		
		Shell shell = new Shell();
		shell.setText("Train animation example");
		shell.setImage(new Image(null,"img/forklift-truck-logo.png"));
		shell.setSize(820, 650);
		
		
		shell.setMenuBar(MenuBar.createMenu(shell));
		
		Composite tabComposite = new Composite(shell, SWT.BORDER);
		tabComposite.setBackground(ColorConstants.lightGray);
		tabComposite.setBounds(5,10, 710, 460);
	
		
	    CTabFolder cTabFolder = Tab.createTabFolder(tabComposite);   
	    cTabFolder.setBounds(5, 5, 595, 445);
		
	    shell.open();
    
		Display display = Display.getDefault();
		while (!shell.isDisposed()) {			
			if (!display.readAndDispatch()) {
			 
			    Point size2 = shell.getSize();
			    tabComposite.setBounds(5,10,size2.x - 190, size2.y - 90);
			    cTabFolder.setSize(size2.x - 205, size2.y - 105);
			    
			    display.sleep();
		   }
		 }
	
	}

	

}
