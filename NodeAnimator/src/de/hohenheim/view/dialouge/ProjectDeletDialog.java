package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.view.mobile.ImageHelper;

/**
 * 
 * @author Arthur Kaul
 *
 */
public class ProjectDeletDialog extends Dialog {
	

	Shell parent; 
    
	/**
	 * 
	 * @param parent
	 * @param style
	 */
	public ProjectDeletDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
     
	/**
	 * 
	 * 
	 */
	 public void open() {
			
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(220, 310);
	    
	  //Fenster  mittig setzen 
	    Toolkit myToolkit = Toolkit.getDefaultToolkit();
	    Dimension myDimension = myToolkit.getScreenSize();
	    dialog.setLocation((int) ((myDimension.getWidth() - dialog.getSize().x) / 2), 
	    		           (int) ((myDimension.getHeight() - dialog.getSize().y) / 2));
	    
	    
	    
	    dialog.setText("Projekt Löschen");
	    dialog.setImage(ImageHelper.delete);
			
		 
	    
		dialog.open();
	}

}
