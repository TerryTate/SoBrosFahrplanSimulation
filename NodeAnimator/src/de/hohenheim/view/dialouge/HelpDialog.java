package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.main.Main;

/**
 * 
 * @author Arthur Kaul
 *
 */
public class HelpDialog extends Dialog{

	Shell parent; 
    
	/**
	 * 
	 * @param parent
	 * @param style
	 */
	public HelpDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	/**
	 * 
	 * 
	 */
	 public void open() {
			
		 
			//Message Box of HelpDialog
			 
		    MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ICON_QUESTION);
		    messageBox.setMessage("Bitte wenden Sie sich an Ihren Hersteller" + "\r\n" +
		            "\r\n" + "(c) Copyright SoBros");
		    messageBox.setText("Help");
		    messageBox.open();
		    
	}

}
