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

public class Main {

	/**
	 * @param args
	 */
	
	Point size;
	
	public static void main(String[] args) {
		
		Shell shell = new Shell();
		shell.setText("Train animation example");
		shell.setImage(new Image(null,"img/forklift-truck-logo.png"));
		shell.setSize(800, 550);
		
		shell.setMenuBar(MenuBar.createMenu(shell));
		
		Composite tabComposite = new Composite(shell, SWT.BORDER);
		tabComposite.setBackground(ColorConstants.gray);
		Point size = shell.getSize();
		tabComposite.setBounds(10,10,size.x - 190, size.y - 70);
		
	    CTabFolder cTabFolder = Tab.createTabFolder(tabComposite);
	    size = shell.getSize();
	    cTabFolder.setSize(size.x - 190, size.y - 70);
		
	    shell.open();
    
		Display display = Display.getDefault();
		while (!shell.isDisposed()) {			
			if (!display.readAndDispatch()) {
			 
			    Point size2 = shell.getSize();
			    tabComposite.setBounds(10,10,size2.x - 190, size2.y - 90);
			    cTabFolder.setSize(size2.x - 190, size2.y - 90);
			    
			    display.sleep();
		   }
		 }
	
	}

	

}
