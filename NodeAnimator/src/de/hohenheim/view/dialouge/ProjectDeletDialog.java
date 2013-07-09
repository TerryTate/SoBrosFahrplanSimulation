package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * The class ProjectDeleteDialog contains the GUI of deleting a project.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */
public class ProjectDeletDialog extends Dialog {
	

	Shell parent; 
    
	/**
	 * A Constructor for a new ProjectDelete dialog.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public ProjectDeletDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
     
	/**
	 * The method open contains view elements of deleting a project
	 */
	 public void open() {
			
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(220, 310);
	    
	 	// Set the window in the middle
	    
	    Toolkit myToolkit = Toolkit.getDefaultToolkit();
	    Dimension myDimension = myToolkit.getScreenSize();
	    dialog.setLocation((int) ((myDimension.getWidth() - dialog.getSize().x) / 2), 
	    		           (int) ((myDimension.getHeight() - dialog.getSize().y) / 2));
	    
	    
	    
	    dialog.setText("Projekt Löschen");
	    dialog.setImage(ImageHelper.delete);
			
		 
	    
		dialog.open();
	}

}
