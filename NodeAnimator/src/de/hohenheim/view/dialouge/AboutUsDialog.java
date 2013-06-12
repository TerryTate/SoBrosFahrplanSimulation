package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import de.hohenheim.controller.main.Main;

public class AboutUsDialog extends Dialog{

	Shell parent;
	

	public AboutUsDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	 public void open() {
	    
		//Message Box of About Us
		 
	    MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ICON_INFORMATION
	            | SWT.YES);
	    messageBox.setMessage
	    ("Fahrplan Animation" + "\r\n" + "\r\n" +
	    "Version: 1.0" + "\r\n" +
	    "(c) Copyright SoBros contributors, 2013." + "\r\n" + "\r\n" +
	    "All rights reserved. " + "\r\n" + "\r\n" +
	    "This product includes software developed by:" + "\r\n" +
	    "Besim Gashi, Arthur Kaul, Daniel Intili, Matthias Zwiesele and Bernd Hofs��" + "\r\n" + "\r\n" +
	    "MatrikelNr: " + "\r\n" +
	    "2739010, 2725383, 2716596, 2716389, 2738846");

	    
		messageBox.setText("About Us");
	    int response = messageBox.open();
	    if (response == SWT.YES)
	    	System.exit(0);
	    

	}

	

}


